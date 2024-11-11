package com.afs.tutrd.local.util

internal enum class AuthKey(
    val key: String
) {
    ACCESS_TOKEN(
        key = "ACCESS_TOKEN"
    ), REFRESH_TOKEN(
        key = "REFRESH_TOKEN"
    )
}