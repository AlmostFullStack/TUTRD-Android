package com.afs.tutrd.network.di

import com.afs.tutrd.network.BuildConfig
import com.afs.tutrd.network.util.adapter.ResultCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        resultCallAdapterFactory: ResultCallAdapterFactory,
        moshi: Moshi,
    ): Retrofit = Retrofit.Builder().apply {
        client(client)
        baseUrl(BuildConfig.BASE_URL)
        addConverterFactory(MoshiConverterFactory.create(moshi))
        addCallAdapterFactory(resultCallAdapterFactory)
    }.build()
}