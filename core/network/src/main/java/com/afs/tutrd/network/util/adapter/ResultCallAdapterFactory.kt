package com.afs.tutrd.network.util.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCallAdapterFactory : CallAdapter.Factory() {

    companion object {
        fun create() = ResultCallAdapterFactory()
    }

    override fun get(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if(getRawType(type) != Call::class.java) return null

        val wrapperType = getParameterUpperBound(0, type as ParameterizedType)

        if (getRawType(wrapperType) != Result::class.java) return null

        val responseType = getParameterUpperBound(0, wrapperType as ParameterizedType)

        return ResultCallAdapter(responseType)
    }
}