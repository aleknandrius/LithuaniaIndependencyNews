package com.telesoftas.lithuaniaindependencynews.main

import com.telesoftas.lithuaniaindependencynews.utils.entity.Article
import com.telesoftas.lithuaniaindependencynews.utils.network.error.ErrorResolver
import com.telesoftas.lithuaniaindependencynews.utils.network.error.NetworkError
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

import org.mockito.BDDMockito
import org.mockito.Mockito
import java.io.IOException

private lateinit var presenter: ArticlesPresenter
private lateinit var view: ArticlesScreen.View
private val ERROR_MESSAGE = "error"
private val model = Mockito.mock(ArticlesScreen.Model::class.java)
private val resolver = Mockito.mock(ErrorResolver::class.java)
private val scheduler = TestScheduler()

class ArticlesPresenterTest {
    @Before
    fun setUp() {
        view = Mockito.mock(ArticlesScreen.View::class.java)
        presenter = ArticlesPresenter(model, scheduler, resolver)
        presenter.takeView(view)
    }

    @Test
    fun onLoadNext_modelHasMoreViewHidesLoadingViewOnSuccess() {
        val response = ArrayList<Article>()
        BDDMockito.given(model.hasMoreArticles()).willReturn(true)
        BDDMockito.given(model.getNextPage()).willReturn(Single.just(response))
        presenter.onLoadNext()
        scheduler.triggerActions()
        Mockito.verify(view).showLoadingView()
        Mockito.verify(view).hideLoadingView()
        Mockito.verify(view).addArticles(response)
    }

    @Test
    fun onLoadNext_modelHasMoreViewHidesLoadingViewOnError() {
        BDDMockito.given(model.hasMoreArticles()).willReturn(true)
        val throwable = IOException(ERROR_MESSAGE)
        val singleError = Single.error<List<Article>>(throwable)
        BDDMockito.given(model.getNextPage()).willReturn(singleError)
        val networkError = NetworkError("", "")
        BDDMockito.given(resolver.resolve(throwable)).willReturn(networkError)

        presenter.onLoadNext()

        scheduler.triggerActions()
        Mockito.verify(view).showLoadingView()
        Mockito.verify(view).hideLoadingView()
        Mockito.verify(view).showErrorDialog(networkError)
    }

    @Test
    fun onLoadNext_modelHasMoreViewShowsLoading() {
        BDDMockito.given(model.hasMoreArticles()).willReturn(true)
        BDDMockito.given(model.getNextPage()).willReturn(
                Single.just(ArrayList<Article>()))

        presenter.onLoadNext()

        scheduler.triggerActions()
        Mockito.verify(view).showLoadingView()
    }

    @Test
    fun onLoadNext_modelHasNoMore() {
        BDDMockito.given(model.hasMoreArticles()).willReturn(false)
        presenter.onLoadNext()
        Mockito.verify(view).setAllItemsLoaded()
    }

    @Test
    fun onScreenLoaded_viewHidesLoadingViewOnSuccess() {
        val response = ArrayList<Article>()
        BDDMockito.given(model.hasMoreArticles()).willReturn(true)
        BDDMockito.given(model.getFirstPage()).willReturn(Single.just(response))
        presenter.onScreenLoaded()
        scheduler.triggerActions()
        Mockito.verify(view).showLoadingView()
        Mockito.verify(view).hideLoadingView()
        Mockito.verify(view).showArticles(response)
    }

    @Test
    fun onScreenLoaded_viewHidesLoadingViewOnError() {
        val throwable = IOException(ERROR_MESSAGE)
        val singleError = Single.error<List<Article>>(throwable)
        BDDMockito.given(model.getFirstPage()).willReturn(singleError)
        val networkError = NetworkError("", "")
        BDDMockito.given(resolver.resolve(throwable)).willReturn(networkError)

        presenter.onScreenLoaded()

        scheduler.triggerActions()
        Mockito.verify(view).showLoadingView()
        Mockito.verify(view).hideLoadingView()
        Mockito.verify(view).showErrorDialog(networkError)
    }

    @Test
    fun onScreenLoaded_viewShowsLoading() {
        BDDMockito.given(model.getFirstPage()).willReturn(
                Single.just(ArrayList<Article>()))

        presenter.onScreenLoaded()

        scheduler.triggerActions()
        Mockito.verify(view).showLoadingView()
    }
}