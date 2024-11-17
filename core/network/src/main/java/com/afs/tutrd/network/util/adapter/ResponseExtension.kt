package com.afs.tutrd.network.util.adapter

import com.afs.tutrd.core.common.error.AppError
import com.afs.tutrd.core.common.error.getErrorByStatusCode
import retrofit2.Response

internal fun <T> Response<T>.toResult(): Result<T> {
    val body = this.body()
    val errorBody = this.errorBody()?.string()

    if (this.isSuccessful) {
        return if (body != null) {
            Result.success(body)
        } else {
            Result.failure(AppError.UnexpectedError("body is null"))
        }
    }

    if (errorBody.isNullOrBlank()) {
        return Result.failure(AppError.UnexpectedError("errorBody is null or blank"))
    }

    try {
        val httpError = getErrorByStatusCode(
            statusCode = this.code(),
            message = this.code().toString()
        )

        return Result.failure(httpError)
    } catch (e: Exception) {
        return  Result.failure(AppError.UnexpectedError("error occurred while parsing error response ${e.message}"))
    }
}