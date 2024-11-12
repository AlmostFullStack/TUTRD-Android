package com.afs.tutrd.network.util.interceptor

import com.afs.tutrd.local.datastore.auth.AuthDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authDataStore: AuthDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (originalRequest.url.encodedPath.contains("/api/v1/token/refresh/")) {
            return chain.proceed(originalRequest)
        }

        val accessToken = runBlocking {
            authDataStore.getAccessToken()
        }

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer ${accessToken.getOrNull()}")
            .build()

        return chain.proceed(newRequest)
    }
}