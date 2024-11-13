package com.easyhz.tutrd.data.auth.datasource.local

interface AuthLocalDataSource {
    suspend fun updateTokens(access: String, refresh: String): Result<Unit>
}