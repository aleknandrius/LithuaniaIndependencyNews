package com.telesoftas.lithuaniaindependencynews

import android.app.Application

class LithuaniaIndependencyNewsApplication : Application() {
    val dependencyRetriever: DependencyRetriever = DependencyRetriever(this)
}

