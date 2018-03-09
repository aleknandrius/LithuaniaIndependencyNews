package com.telesoftas.lithuaniaindependencynews.main

import com.telesoftas.lithuaniaindependencynews.utils.base.presenter.BasePresenter
import com.telesoftas.lithuaniaindependencynews.utils.base.view.BaseNetworkView
import com.telesoftas.lithuaniaindependencynews.utils.entity.Article
import io.reactivex.Single

interface ArticlesScreen {
    interface View : BaseNetworkView {
        fun showArticles(list: List<Article>)
        fun setAllItemsLoaded()
    }

    interface Presenter : BasePresenter<View> {
        fun onScreenLoaded()
        fun onLoadNext()
    }

    interface Model {
        fun getNextPage(): Single<List<Article>>
        fun getFirstPage(): Single<List<Article>>
        fun hasMoreArticles(): Boolean
    }
}