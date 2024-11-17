package com.afs.tutrd.network.model.auth.request

import com.squareup.moshi.Json

data class RegisterBody(
    @Json(name = "firebase_uid")
    val uid: String,
    val name: String,
    val phone: String,
    val specialty: List<String>
)