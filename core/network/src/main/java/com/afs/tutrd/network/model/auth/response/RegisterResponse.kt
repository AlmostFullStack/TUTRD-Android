package com.afs.tutrd.network.model.auth.response

import com.squareup.moshi.Json

data class RegisterResponse(
//    val user: User,
    @Json(name = "refresh_token")
    val refreshToken: String,
    @Json(name = "access_token")
    val accessToken: String,
)