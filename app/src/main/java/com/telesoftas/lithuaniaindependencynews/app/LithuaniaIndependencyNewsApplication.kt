package com.telesoftas.lithuaniaindependencynews.app

import android.app.Activity
import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.telesoftas.dagger2dojo.app.DaggerAppComponent
import com.telesoftas.lithuaniaindependencynews.DependencyRetriever
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class LithuaniaIndependencyNewsApplication : Application(), HasActivityInjector {
    @Inject  lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    val dependencyRetriever: DependencyRetriever = DependencyRetriever(this)
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .create(this)
                .inject(this)
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}

