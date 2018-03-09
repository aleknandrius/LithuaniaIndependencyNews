package com.telesoftas.lithuaniaindependencynews.utils.network.error

import android.content.res.Resources
import com.telesoftas.lithuaniaindependencynews.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito

class DefaultNetworkErrorMessengerTest {
    private val MESSAGE = "message"
    private lateinit var resources: Resources
    private lateinit var messenger: DefaultNetworkErrorMessenger

    @Before
    @Throws(Exception::class)
    fun setUp() {
        resources = Mockito.mock(Resources::class.java)
        messenger = DefaultNetworkErrorMessenger(resources)
    }

    @Test
    fun getNoInternetMessage_returnsNoInternetMessage() {
        given(resources.getString(R.string.network_error_message_no_internet))
                .willReturn(MESSAGE)

        assertEquals("Incorrect message", MESSAGE, messenger.getNoInternetMessage())
    }

    @Test
    fun getServerNotRespondingMessage_returnsNotRespondingMessage() {
        given(resources.getString(R.string.network_error_message_no_server_respond))
                .willReturn(MESSAGE)

        assertEquals("Incorrect message",
                MESSAGE, messenger.getServerNotRespondingMessage())
    }

    @Test
    fun getConnectionLostMessage_returnsConnectionLostMessage() {
        given(resources.getString(R.string.network_error_message_connection_lost))
                .willReturn(MESSAGE)

        assertEquals("Incorrect message", MESSAGE, messenger.getConnectionLostMessage())
    }

    @Test
    fun getUnknownErrorMessage_returnsUnknownErrorMessage() {
        given(resources.getString(R.string.network_error_message_unknown))
                .willReturn(MESSAGE)

        assertEquals("Incorrect message", MESSAGE, messenger.getUnknownErrorMessage())
    }
}