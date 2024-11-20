package com.afs.tutrd.network.model.auth.response

import com.squareup.moshi.Json

data class VerifyResponse(
    @Json(name = "user_exists")
    val userExists: Boolean,
//    @Json(name = "user")
//    val user: User?,
    @Json(name = "refresh_token")
    val refreshToken: String?,
    @Json(name = "access_token")
    val accessToken: String?
)