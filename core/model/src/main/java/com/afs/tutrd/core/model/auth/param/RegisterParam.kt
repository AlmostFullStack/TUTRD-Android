package com.afs.tutrd.core.model.auth.param

data class RegisterParam(
    val name: String,
    val phone: String,
    val specialty: List<String>
)
