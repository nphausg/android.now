/*
 * Created by nphau on 4/1/23, 2:26 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 2:26 PM
 */

package com.nphausg.app.now.shared.di.module

import com.nphausg.app.now.shared.data.environment.EnvironmentProvider
import com.nphausg.app.now.shared.internal.data.environment.EnvironmentProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface EnvironmentModule {

    @Binds
    fun bindEmbeddedServer(embeddedServer: EnvironmentProviderImpl): EnvironmentProvider

}