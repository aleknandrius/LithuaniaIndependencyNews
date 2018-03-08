package com.telesoftas.lithuaniaindependencynews.utils.base.view

import com.telesoftas.lithuaniaindependencynews.utils.network.error.NetworkError


interface BaseNetworkView {

    fun showErrorDialog(error: NetworkError)

    fun showLoadingView() {}

    fun hideLoadingView() {}

    fun hideSoftKeyboard()
}