package com.afs.tutrd.network.model.auth.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)

