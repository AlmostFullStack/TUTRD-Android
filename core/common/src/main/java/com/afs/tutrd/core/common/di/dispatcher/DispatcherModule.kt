package com.afs.tutrd.core.common.di.dispatcher

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @Dispatcher(AppDispatchers.IO)
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}