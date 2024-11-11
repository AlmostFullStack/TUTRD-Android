package com.afs.tutrd.core.model.auth

data class VerifyUser(
    val userExists: Boolean,
    val user: User?,
    val refreshToken: String?,
    val accessToken: String?
)