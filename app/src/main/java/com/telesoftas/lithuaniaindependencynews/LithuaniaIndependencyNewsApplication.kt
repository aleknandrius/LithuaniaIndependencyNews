package com.telesoftas.lithuaniaindependencynews

import android.app.Application
import com.squareup.leakcanary.LeakCanary



class LithuaniaIndependencyNewsApplication : Application() {
    val dependencyRetriever: DependencyRetriever = DependencyRetriever(this)
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}

