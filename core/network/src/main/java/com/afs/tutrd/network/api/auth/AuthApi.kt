package com.afs.tutrd.network.api.auth

import com.afs.tutrd.network.model.auth.request.RegisterBody
import com.afs.tutrd.network.model.auth.request.TokenBody
import com.afs.tutrd.network.model.auth.response.RegisterResponse
import com.afs.tutrd.network.model.auth.response.TokenResponse
import com.afs.tutrd.network.model.auth.response.VerifyResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/v1/users/verify/")
    suspend fun verify(
        @Header("Authorization") token: String,
    ): Result<VerifyResponse>

    @POST("/api/v1/users/register/")
    suspend fun register(
        @Body body: RegisterBody
    ): Result<RegisterResponse>

    @POST("/api/v1/token/refresh/")
    suspend fun reissueAccessToken(
        @Body body: TokenBody
    ): Result<TokenResponse>
}