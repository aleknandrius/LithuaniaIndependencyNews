package com.telesoftas.lithuaniaindependencynews.main

import com.telesoftas.lithuaniaindependencynews.utils.entity.ArticleList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesService {
    @GET("everything")
    fun articles(@Query("q")  queryString: String,
                 @Query("page") pageIndex: Int,
                 @Query("pageSize") pageSize: Int,
                 @Query("apiKey") apiKey: String
    ): Single<ArticleList>
}