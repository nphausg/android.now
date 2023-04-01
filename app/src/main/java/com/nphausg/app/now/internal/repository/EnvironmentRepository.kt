/*
 * Created by nphau on 4/1/23, 1:04 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 1:04 PM
 */

package com.nphausg.app.now.internal.repository

import com.nphausg.app.now.domain.environment.EmbeddedServer
import javax.inject.Inject

class EnvironmentRepository @Inject constructor(
    private var embeddedServer: EmbeddedServer
) {

    fun startServer() {
        embeddedServer.startServer()
    }

    fun stopServer() {
        embeddedServer.stopServer()
    }
}