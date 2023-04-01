/*
 * Created by nphau on 4/1/23, 12:24 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 12:24 PM
 */

package com.nphausg.app.now.di

import com.nphausg.app.now.domain.environment.EmbeddedServer
import com.nphausg.app.now.internal.environment.EmbeddedServerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface EnvironmentModules {

    @Binds
    fun bindEmbeddedServer(embeddedServer: EmbeddedServerImpl): EmbeddedServer

}