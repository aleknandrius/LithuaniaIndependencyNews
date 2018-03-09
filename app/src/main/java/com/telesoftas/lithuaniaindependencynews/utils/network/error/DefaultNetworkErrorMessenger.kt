package com.telesoftas.lithuaniaindependencynews.utils.network.error

import android.content.res.Resources
import com.telesoftas.lithuaniaindependencynews.R

class DefaultNetworkErrorMessenger(private val resources: Resources) : NetworkErrorMessenger {
    override fun getNoInternetMessage(): String {
        return resources.getString(R.string.network_error_message_no_internet)
    }

    override fun getServerNotRespondingMessage(): String {
        return resources.getString(R.string.network_error_message_no_server_respond)
    }

    override fun getConnectionLostMessage(): String {
        return resources.getString(R.string.network_error_message_connection_lost)
    }

    override fun getUnknownErrorMessage(): String {
        return resources.getString(R.string.network_error_message_unknown)
    }
}