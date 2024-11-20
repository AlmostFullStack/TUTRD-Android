package com.afs.tutrd.network.util.authenticator

import com.afs.tutrd.local.datastore.auth.AuthDataStore
import com.afs.tutrd.network.api.auth.AuthApi
import com.afs.tutrd.network.model.auth.request.TokenBody
import com.afs.tutrd.network.model.auth.response.TokenResponse
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val authDataStore: AuthDataStore,
    private val authApi: AuthApi
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code != 401) return null
        val refreshToken = fetchRefreshToken() ?: return null
        val token = reissueRefreshToken(refreshToken).getOrNull() ?: return null
        val newRequest = response.request.newBuilder().apply {
            removeHeader("Authorization")
            addHeader("Authorization", "Bearer ${token.accessToken}")
        }.build()

        if (token.refreshToken == refreshToken) {
            updateLocalAccessToken(token.accessToken)
        } else {
            updateLocalTokens(token)
        }
        return newRequest
    }

    private fun fetchRefreshToken(): String? = runBlocking {
        return@runBlocking authDataStore.getRefreshToken().getOrNull()
    }

    private fun reissueRefreshToken(refreshToken: String): Result<TokenResponse> = runBlocking {
        return@runBlocking authApi.reissueAccessToken(TokenBody(refreshToken = refreshToken))
    }

    private fun updateLocalAccessToken(accessToken: String) = runBlocking {
        return@runBlocking authDataStore.updateAccessToken(accessToken)
    }

    private fun updateLocalTokens(token: TokenResponse) = runBlocking {
        return@runBlocking authDataStore.updateTokens(access = token.accessToken, refresh = token.refreshToken)
    }

}