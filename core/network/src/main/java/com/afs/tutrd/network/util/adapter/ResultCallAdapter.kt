package com.afs.tutrd.network.util.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ResultCallAdapter(
    private val responseType: Type,
): CallAdapter<Type, Call<Result<Type>>> {
    override fun responseType(): Type = responseType
    override fun adapt(call: Call<Type>): Call<Result<Type>> = ResultCall(call)
}
