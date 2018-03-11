package com.telesoftas.lithuaniaindependencynews.main.dagger

import android.app.Application
import com.telesoftas.lithuaniaindependencynews.DependencyRetriever
import com.telesoftas.lithuaniaindependencynews.dependencyRetriever
import com.telesoftas.lithuaniaindependencynews.main.*
import com.telesoftas.lithuaniaindependencynews.utils.network.error.ErrorResolver
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers


@Module
abstract class ArticlesModule() {

    @Binds
    abstract fun bindPresenter(presenter: ArticlesPresenter): ArticlesScreen.Presenter

    @Binds
    abstract fun bindModel(model: ArticlesModel): ArticlesScreen.Model

    @Module
    companion object {
        @JvmStatic @Provides
        fun providesSheduler(): Scheduler = AndroidSchedulers.mainThread()

        @JvmStatic @Provides
        fun providesRetriever(application: Application): DependencyRetriever {
            return application.dependencyRetriever
        }

        @JvmStatic @Provides
        fun providesService(retriever: DependencyRetriever): ArticlesService {
            return retriever.getService(ArticlesService::class.java)
        }

        @JvmStatic @Provides
        fun providesResolver(retriever: DependencyRetriever): ErrorResolver {
            return retriever.networkErrorResolver
        }
    }
}