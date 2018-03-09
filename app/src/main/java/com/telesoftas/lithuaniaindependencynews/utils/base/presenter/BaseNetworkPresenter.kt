package com.telesoftas.lithuaniaindependencynews.utils.base.presenter

import com.telesoftas.lithuaniaindependencynews.utils.base.view.BaseNetworkView
import com.telesoftas.lithuaniaindependencynews.utils.network.error.ErrorResolver

open class BaseNetworkPresenter<T : BaseNetworkView>(
        private val resolver: ErrorResolver
) : BasePresenterImpl<T>(), NetworkPresenter {
    override fun handleError(throwable: Throwable) {
        onView { hideLoadingView() }
        val error = resolver.resolve(throwable)
        onView { showErrorDialog(error) }
    }
}