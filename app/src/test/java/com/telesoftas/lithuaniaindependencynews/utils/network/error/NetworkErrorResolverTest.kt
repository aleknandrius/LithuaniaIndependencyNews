package com.telesoftas.lithuaniaindependencynews.utils.formater

import com.telesoftas.lithuaniaindependencynews.utils.network.error.NetworkErrorMessenger
import com.telesoftas.lithuaniaindependencynews.utils.network.error.NetworkErrorResolver
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkErrorResolverTest {
    private val CODE = "404"
    private val MESSAGE = "message"
    private lateinit var messenger: NetworkErrorMessenger
    private lateinit var resolver: NetworkErrorResolver

    @Before
    @Throws(Exception::class)
    fun setUp() {
        messenger = Mockito.mock(NetworkErrorMessenger::class.java)
        resolver = NetworkErrorResolver(messenger)
    }

    @Test
    @Throws(Exception::class)
    fun testResolve_resolverResolvesUnknownHostException() {
        val error = UnknownHostException(MESSAGE)
        BDDMockito.given(messenger.getNoInternetMessage()).willReturn(MESSAGE)

        assertMessage(MESSAGE, resolver.resolve(error).message)
    }

    @Test
    @Throws(Exception::class)
    fun testResolve_resolverResolvesSocketTimeoutException() {
        val error = SocketTimeoutException(MESSAGE)
        BDDMockito.given(messenger.getServerNotRespondingMessage()).willReturn(MESSAGE)

        assertMessage(MESSAGE, resolver.resolve(error).message)
    }

    @Test
    @Throws(Exception::class)
    fun testResolve_resolverResolvesIOException() {
        val error = IOException(MESSAGE)
        BDDMockito.given(messenger.getConnectionLostMessage()).willReturn(MESSAGE)

        assertMessage(MESSAGE, resolver.resolve(error).message)
    }

    @Test
    @Throws(Exception::class)
    fun testResolve_resolverResolvesUnknownException() {
        val error = Throwable(MESSAGE)
        BDDMockito.given(messenger.getUnknownErrorMessage()).willReturn(MESSAGE)

        assertMessage(MESSAGE, resolver.resolve(error).message)
    }

    @Test
    @Throws(Exception::class)
    fun testResolve_resolverHttpException() {
        val exception = HttpException(Response.error<HttpException>(
                401, ResponseBody.create(null, "content")))

        val error = resolver.resolve(exception)

        Assert.assertEquals("incorrect error title",
                "Response.error()", error.title)
        Assert.assertEquals("incorrect error message",
                "HTTP 401 Response.error()", error.message)
    }

    @Test
    @Throws(Exception::class)
    fun testResolve_resolverUnknownHttpException() {
        BDDMockito.given(messenger.getUnknownErrorMessage()).willReturn(MESSAGE)

        val exception = HttpException(Response.error<HttpException>(
                402, ResponseBody.create(null, "content")))

        assertMessage(MESSAGE, resolver.resolve(exception).message)
    }

    private fun assertMessage(expected: String, actual: String) {
        Assert.assertEquals("Incorrect message", expected, actual)
    }
}