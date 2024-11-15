package com.easyhz.tutrd.data.category.datasource.local

import android.content.Context
import com.afs.tutrd.core.model.category.Category
import com.afs.tutrd.core.model.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CategoryLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
): CategoryLocalDataSource {
    override fun getCategories(): Result<List<Category>> {
        return runCatching {
            val inputStream = context.resources.openRawResource(R.raw.category)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            Json.decodeFromString<List<Category>>(jsonString)
        }
    }
}