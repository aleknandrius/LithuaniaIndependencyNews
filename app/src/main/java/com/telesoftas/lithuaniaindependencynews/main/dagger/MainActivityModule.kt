package com.telesoftas.lithuaniaindependencynews.main.dagger

import com.telesoftas.lithuaniaindependencynews.main.ArticlesFragment
import com.telesoftas.lithuaniaindependencynews.utils.dagger.PerFragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @PerFragmentScope
    @ContributesAndroidInjector(modules = [ArticlesModule::class])
    abstract fun mainFragmentInjector(): ArticlesFragment

}