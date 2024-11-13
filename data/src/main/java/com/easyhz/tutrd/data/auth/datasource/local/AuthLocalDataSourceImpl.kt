package com.easyhz.tutrd.data.auth.datasource.local

import com.afs.tutrd.local.datastore.auth.AuthDataStore
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val authDataStore: AuthDataStore
): AuthLocalDataSource {
    override suspend fun updateTokens(access: String, refresh: String): Result<Unit> = runCatching {
        authDataStore.updateTokens(access, refresh)
    }
}