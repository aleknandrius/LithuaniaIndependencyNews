package com.telesoftas.lithuaniaindependencynews.main

import com.nhaarman.mockito_kotlin.given
import com.telesoftas.lithuaniaindependencynews.utils.entity.Article
import com.telesoftas.lithuaniaindependencynews.utils.entity.ArticleList
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

private val service = Mockito.mock(ArticlesService::class.java)
private val articles = ArticleList("",-1,ArrayList<Article>())
private val response = Single.just(articles)
private lateinit var model: ArticlesModel

class ArticlesModelTest {
    @Before
    fun setUp() {
        model = ArticlesModel(service)
        given(service.articles(
                Mockito.anyString(),
                Mockito.anyInt(),
                Mockito.anyInt(),
                Mockito.anyString()
        )).willReturn(response)
    }

    @Test
    fun getFirstPage() {

    }

    @Test
    fun getNextPage() {
    }

    @Test
    fun hasMoreArticles_returnTrue() {

    }

    @Test
    fun hasMoreArticles_returnFalse() {

    }

}