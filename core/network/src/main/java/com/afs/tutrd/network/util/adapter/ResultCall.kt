package com.afs.tutrd.network.util.adapter

import com.afs.tutrd.core.common.error.AppError
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


internal class ResultCall<T>(
    private val delegate: Call<T>,
) : Call<Result<T>> {
    override fun clone(): Call<Result<T>> = ResultCall(delegate.clone())

    override fun execute(): Response<Result<T>> = throw UnsupportedOperationException()

    override fun enqueue(callback: Callback<Result<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.onResponse(this@ResultCall, Response.success(response.toResult()))
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                val failure = when (throwable) {
                    is IOException -> AppError.NetworkConnectionError
                    else -> AppError.UnexpectedError(throwable.message ?: "Unknown error")
                }
                callback.onResponse(this@ResultCall, Response.success(Result.failure(failure)))
            }
        })
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}
