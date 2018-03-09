package com.telesoftas.lithuaniaindependencynews.main

import com.telesoftas.lithuaniaindependencynews.BuildConfig.API_KEY
import com.telesoftas.lithuaniaindependencynews.utils.entity.Article
import io.reactivex.Single

class ArticlesModel(private val articleServices: ArticlesService
) : ArticlesScreen.Model {
    private var page = 0
    var hasMoreArticles = true

    override fun getFirstPage(): Single<List<Article>> {
        page = 0
        hasMoreArticles = true
        return getArticlePage()
    }

    override fun getNextPage(): Single<List<Article>> {
        return getArticlePage()
    }

    private fun getArticlePage(): Single<List<Article>> {
        return articleServices.articles(SEARCH_QUERY, page + 1, PAGE_SIZE, API_KEY).map {
            page ++
            if(it.articles.size< PAGE_SIZE){
                hasMoreArticles = false
            }
            it.articles
        }
    }

    override fun hasMoreArticles(): Boolean {
        return hasMoreArticles
    }

    companion object {
        const val SEARCH_QUERY= "lithuania independence"
        const val PAGE_SIZE= 20
    }
}