package com.telesoftas.lithuaniaindependencynews.utils.network.error

interface NetworkErrorMessenger {
    fun getNoInternetMessage(): String

    fun getServerNotRespondingMessage(): String

    fun getConnectionLostMessage(): String

    fun getUnknownErrorMessage(): String
}