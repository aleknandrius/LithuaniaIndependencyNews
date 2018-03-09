package com.telesoftas.lithuaniaindependencynews.main

import com.telesoftas.lithuaniaindependencynews.utils.base.presenter.BaseNetworkPresenter
import com.telesoftas.lithuaniaindependencynews.utils.entity.Article
import com.telesoftas.lithuaniaindependencynews.utils.network.error.ErrorResolver
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class ArticlesPresenter(
        private val model: ArticlesScreen.Model,
        private val scheduler: Scheduler,
        errorResolver: ErrorResolver
) : BaseNetworkPresenter<ArticlesScreen.View>(errorResolver),
        ArticlesScreen.Presenter {
    override fun onLoadNext() {
        if (model.hasMoreArticles()) {
            model.getNextPage()
                    .observeOn(scheduler)
                    .subscribe(object : SingleObserver<List<Article>> {
                        override fun onSubscribe(disposable: Disposable) {
                            onView { showLoadingView() }
                        }

                        override fun onError(throwable: Throwable) {
                            handleError(throwable)
                        }

                        override fun onSuccess(response: List<Article>) {
                            onView {
                                hideLoadingView()
                                showArticles(response)
                            }
                        }
                    })
        } else {
            onView {
                setAllItemsLoaded()
            }
        }
    }

    override fun onScreenLoaded() {
        model.getFirstPage()
                .observeOn(scheduler)
                .subscribe(object : SingleObserver<List<Article>> {
                    override fun onSubscribe(disposable: Disposable) {
                        onView { showLoadingView() }
                    }

                    override fun onError(throwable: Throwable) {
                        handleError(throwable)
                    }

                    override fun onSuccess(response: List<Article>) {
                        onView {
                            hideLoadingView()
                            showArticles(response)
                        }
                    }
                })
    }
}