package com.easyhz.tutrd.data.auth.di

import com.easyhz.tutrd.data.auth.datasource.local.AuthLocalDataSource
import com.easyhz.tutrd.data.auth.datasource.local.AuthLocalDataSourceImpl
import com.easyhz.tutrd.data.auth.datasource.remote.AuthRemoteDataSource
import com.easyhz.tutrd.data.auth.datasource.remote.AuthRemoteDataSourceImpl
import com.easyhz.tutrd.data.auth.repository.AuthRepositoryImpl
import com.easyhz.tutrd.domain.auth.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthDataModule {

    @Binds
    fun bindAuthLocalDataSource(
        authLocalDataSourceImpl: AuthLocalDataSourceImpl
    ): AuthLocalDataSource

    @Binds
    fun bindAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Binds
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}