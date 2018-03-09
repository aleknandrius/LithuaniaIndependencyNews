package com.telesoftas.lithuaniaindependencynews.utils.base.presenter

interface BasePresenter<in V> {
    fun takeView(view: V)

    fun dropView()
}
