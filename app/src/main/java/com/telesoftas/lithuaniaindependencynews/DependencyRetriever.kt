package com.telesoftas.lithuaniaindependencynews

import android.content.Context
import com.telesoftas.lithuaniaindependencynews.utils.network.error.DefaultNetworkErrorMessenger
import com.telesoftas.lithuaniaindependencynews.utils.network.error.NetworkErrorResolver
import com.telesoftas.lithuaniaindependencynews.utils.network.RestClientBuilder
import retrofit2.Retrofit

class DependencyRetriever(private val context: Context) {
    private val retrofit: Retrofit by lazy { RestClientBuilder().build() }

    fun <T> getService(clazz: Class<T>): T = retrofit.create(clazz)

    val networkErrorResolver by lazy {
        val messenger = DefaultNetworkErrorMessenger(context.resources)
        NetworkErrorResolver(messenger)
    }
}

val Context.dependencyRetriever: DependencyRetriever
    get() = (applicationContext as LithuaniaIndependencyNewsApplication).dependencyRetriever