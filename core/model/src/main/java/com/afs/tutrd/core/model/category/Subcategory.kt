package com.afs.tutrd.core.model.category

import kotlinx.serialization.Serializable

@Serializable
data class Subcategory(
    val code: String,
    val name: String
)