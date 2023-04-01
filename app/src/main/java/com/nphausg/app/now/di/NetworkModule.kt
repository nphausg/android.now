/*
 * Created by nphau on 4/1/23, 12:09 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 12:09 PM
 */

package com.nphausg.app.now.di

import com.nphausg.app.now.domain.environment.EnvironmentProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, environment: EnvironmentProvider): Retrofit {
        return Retrofit.Builder()
            .baseUrl(environment.baseUrl)
            .client(okHttpClient)
            .build()
    }
}