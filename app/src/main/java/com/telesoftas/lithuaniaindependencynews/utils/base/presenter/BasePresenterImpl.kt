package com.telesoftas.lithuaniaindependencynews.utils.base.presenter

open class BasePresenterImpl<V> : BasePresenter<V> {
    private var view: V? = null

    override fun takeView(view: V) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

    fun hasView() = view != null

    fun onView(action: V.() -> Unit) {
        if (hasView()) {
            action.invoke(view!!)
        }
    }
}
