/*
 * Created by nphau on 4/1/23, 2:25 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 2:25 PM
 */

package com.nphausg.app.now.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nphausg.app.now.shared.data.environment.EnvironmentProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
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

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, environment: EnvironmentProvider): Retrofit {
        return Retrofit.Builder()
            .baseUrl(environment.baseUrl)
            .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
            .client(okHttpClient)
            .build()
    }
}