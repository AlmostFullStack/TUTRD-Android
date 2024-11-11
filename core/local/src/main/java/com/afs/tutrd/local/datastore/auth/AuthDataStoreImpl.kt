package com.afs.tutrd.local.datastore.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.afs.tutrd.local.di.Auth
import com.afs.tutrd.local.util.AuthKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthDataStoreImpl @Inject constructor(
    @Auth private val dataStore: DataStore<Preferences>
): AuthDataStore {
    private val accessToken = stringPreferencesKey(AuthKey.ACCESS_TOKEN.key)
    private val refreshToken = stringPreferencesKey(AuthKey.REFRESH_TOKEN.key)

    override suspend fun getAccessToken(): Result<String> {
        val preferences = dataStore.data.first()
        return preferences[accessToken]?.let {
            Result.success(it)
        } ?: Result.failure(generateNullException(AuthKey.ACCESS_TOKEN))
    }

    override suspend fun getRefreshToken(): Result<String>{
        val preferences = dataStore.data.first()
        return preferences[refreshToken]?.let {
            Result.success(it)
        } ?: Result.failure(generateNullException(AuthKey.REFRESH_TOKEN))
    }

    override suspend fun deleteToken() {
        dataStore.edit { preferences ->
            preferences.remove(accessToken)
            preferences.remove(refreshToken)
        }
    }

    override suspend fun updateAccessToken(access: String) {
        dataStore.edit { preferences ->
            preferences[accessToken] = access
        }
    }

    override suspend fun updateTokens(access: String, refresh: String) {
        dataStore.edit { preferences ->
            preferences[accessToken] = access
            preferences[refreshToken] = refresh
        }
    }

    private fun generateNullException(authKey: AuthKey): Exception {
        return Exception("${authKey.key} is null")
    }
}