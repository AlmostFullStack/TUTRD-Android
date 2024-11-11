package com.afs.tutrd.local.datastore.auth

interface AuthDataStore {
    suspend fun getAccessToken(): Result<String>
    suspend fun getRefreshToken(): Result<String>
    suspend fun deleteToken()
    suspend fun updateAccessToken(access: String)
    suspend fun updateTokens(access: String, refresh: String)
}