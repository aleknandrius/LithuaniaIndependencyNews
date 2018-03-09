package com.telesoftas.lithuaniaindependencynews.utils.network.error

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkErrorResolver(private val messenger: NetworkErrorMessenger) : ErrorResolver {
    override fun resolve(throwable: Throwable): NetworkError {
        return when (throwable) {
            is UnknownHostException ->
                NetworkError(message = messenger.getNoInternetMessage())
            is SocketTimeoutException ->
                NetworkError(message = messenger.getServerNotRespondingMessage())
            is IOException ->
                NetworkError(message = messenger.getConnectionLostMessage())
            is HttpException ->
                resolveHttpException(throwable)
            else ->
                NetworkError(message = messenger.getUnknownErrorMessage())
        }
    }

    private fun resolveHttpException(throwable: HttpException): NetworkError {
        return when (throwable.code()) {
            401 ->
                NetworkError(throwable.message(), throwable.localizedMessage)
            else ->
                NetworkError(message = messenger.getUnknownErrorMessage())
        }
    }
}