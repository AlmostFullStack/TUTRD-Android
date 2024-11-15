package com.easyhz.tutrd.data.category.datasource.local

import com.afs.tutrd.core.model.category.Category

interface CategoryLocalDataSource {
    fun getCategories(): Result<List<Category>>
}