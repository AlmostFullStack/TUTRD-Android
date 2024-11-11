package com.afs.tutrd.core.model.category

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val code: String,
    val name: String,
    val subcategories: List<Subcategory>
)