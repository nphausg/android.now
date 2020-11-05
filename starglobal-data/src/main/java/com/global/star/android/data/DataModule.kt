package com.global.star.android.data

import com.global.star.android.data.api.UnAuthorizationHeaderInterceptor
import com.global.star.android.data.api.UserNetworkModule
import com.global.star.android.data.api.UserNetworkService
import com.global.star.android.data.common.HeaderType
import com.global.star.android.data.repositories.GithubUserRepositoryImpl
import com.global.star.android.domain.repositories.GithubUserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [DataRepositoryModule::class])
class DataModule {

    @Singleton
    @Provides
    fun provideGithubUserNetworkService(): UserNetworkService {
        return UserNetworkModule.getInstance().userService()
    }

    @Singleton
    @Provides
    @Named(HeaderType.UN_AUTHORIZATION)
    fun provideUnAuthorizationHeader(): Interceptor {
        return UnAuthorizationHeaderInterceptor()
    }

    @Singleton
    @Provides
    @Named(HeaderType.LOGGER)
    fun provideLoggerHeader(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
        }
    }
}

@Module
interface DataRepositoryModule {
    @Binds
    @Singleton
    fun bindGithubUserRepository(githubUserRepositoryImpl: GithubUserRepositoryImpl): GithubUserRepository
}