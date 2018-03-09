package com.telesoftas.lithuaniaindependencynews.utils.network.error

interface ErrorResolver {
    fun resolve(throwable: Throwable): NetworkError
}