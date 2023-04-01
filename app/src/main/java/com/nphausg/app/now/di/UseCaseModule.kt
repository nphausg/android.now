/*
 * Created by nphau on 4/1/23, 1:05 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 1:05 PM
 */

package com.nphausg.app.now.di

import com.nphausg.app.now.domain.usecase.EnvironmentUseCase
import com.nphausg.app.now.domain.usecase.EnvironmentUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindEnvironmentUseCase(environmentUseCase: EnvironmentUseCaseImpl): EnvironmentUseCase

}