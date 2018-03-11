package com.telesoftas.lithuaniaindependencynews.main

import com.nhaarman.mockito_kotlin.given
import com.telesoftas.lithuaniaindependencynews.BuildConfig
import com.telesoftas.lithuaniaindependencynews.utils.entity.Article
import com.telesoftas.lithuaniaindependencynews.utils.entity.ArticleList
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

private lateinit var service: ArticlesService
private val article = Article("", "", "", "", "", "")

private val articlesEmpty = ArticleList(ArrayList())
private lateinit var articlesFull: ArticleList
private val responseNotFull = Single.just(articlesEmpty)
private lateinit var responseFull: Single<ArticleList>
private lateinit var model: ArticlesModel

class ArticlesModelTest {
    @Before
    fun setUp() {
        val list = ArrayList<Article>()
        for (i in 0..19) {
            list.add(article)
        }
        articlesFull = ArticleList(list)
        responseFull = Single.just(articlesFull)
        service = Mockito.mock(ArticlesService::class.java)
        model = ArticlesModel(service)

    }

    @Test
    fun getFirstPage_hasMoreItemsFalse() {
        given(service.articles(
                ArticlesModel.SEARCH_QUERY,
                1,
                ArticlesModel.PAGE_SIZE,
                BuildConfig.API_KEY
        )).willReturn(responseNotFull)
        val response = model.getFirstPage().toFuture().get()
        val responseExpected = responseNotFull.toFuture().get().articles
        Assert.assertEquals(response, responseExpected)
        Assert.assertTrue(!model.hasMoreArticles())
    }

    @Test
    fun getFirstPage_hasMoreItemsTrue() {
        given(service.articles(
                ArticlesModel.SEARCH_QUERY,
                1,
                ArticlesModel.PAGE_SIZE,
                BuildConfig.API_KEY
        )).willReturn(responseFull)
        val response = model.getFirstPage().toFuture().get()
        val responseExpected = responseFull.toFuture().get().articles
        Assert.assertEquals(response, responseExpected)
        Assert.assertTrue(model.hasMoreArticles() == true)
    }

    @Test
    fun getNextPage_hasMoreItemsTrue() {
        given(service.articles(
                ArticlesModel.SEARCH_QUERY,
                1,
                ArticlesModel.PAGE_SIZE,
                BuildConfig.API_KEY
        )).willReturn(responseFull)
        val response = model.getNextPage().toFuture().get()
        val responseExpected = responseFull.toFuture().get().articles
        Assert.assertEquals(response, responseExpected)
        Assert.assertTrue(model.hasMoreArticles())
    }

    @Test
    fun getNextPage_hasMoreItemsFalse() {
        given(service.articles(
                ArticlesModel.SEARCH_QUERY,
                1,
                ArticlesModel.PAGE_SIZE,
                BuildConfig.API_KEY
        )).willReturn(responseNotFull)
        val response = model.getNextPage().toFuture().get()
        val responseExpected = responseNotFull.toFuture().get().articles
        Assert.assertEquals(response, responseExpected)
        Assert.assertTrue(!model.hasMoreArticles())
    }
}