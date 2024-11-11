package com.afs.tutrd.local.di

import com.afs.tutrd.local.datastore.auth.AuthDataStore
import com.afs.tutrd.local.datastore.auth.AuthDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreModule {
    @Binds
    fun bindAuthDataStore(
        authDataStoreImpl: AuthDataStoreImpl
    ): AuthDataStore
}