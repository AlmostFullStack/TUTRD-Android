package com.afs.tutrd.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.afs.tutrd.local.util.authDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataStoreConfigModule {
    companion object {

        @Auth
        @Singleton
        @Provides
        fun provideAuthDataStorePreferences(
            @ApplicationContext context: Context
        ): DataStore<Preferences> =
            context.authDataStore
    }
}