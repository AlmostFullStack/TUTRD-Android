package com.afs.tutrd.network.model.auth.request

import com.squareup.moshi.Json

data class TokenBody(
    @Json(name = "refresh")
    val refreshToken: String
)
