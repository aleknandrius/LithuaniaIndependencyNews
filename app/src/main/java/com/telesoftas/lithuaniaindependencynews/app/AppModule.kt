package com.telesoftas.dagger2dojo.app


import android.app.Application
import com.telesoftas.lithuaniaindependencynews.app.LithuaniaIndependencyNewsApplication
import com.telesoftas.lithuaniaindependencynews.main.MainActivity
import com.telesoftas.lithuaniaindependencynews.main.dagger.MainActivityModule
import com.telesoftas.lithuaniaindependencynews.utils.dagger.PerActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton


@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun application(app: LithuaniaIndependencyNewsApplication): Application

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun mainActivityInjector(): MainActivity
}