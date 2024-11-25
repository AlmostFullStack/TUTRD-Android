package com.afs.tutrd.network.di

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class Debug

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class HttpLoggingLevel