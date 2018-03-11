package com.telesoftas.dagger2dojo.app

import com.telesoftas.lithuaniaindependencynews.app.LithuaniaIndependencyNewsApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class])
interface AppComponent : AndroidInjector<LithuaniaIndependencyNewsApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<LithuaniaIndependencyNewsApplication>()
}