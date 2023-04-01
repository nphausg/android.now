/*
 * Created by nphau on 4/1/23, 1:02 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 12:07 PM
 */

package com.nphausg.app.now.domain.environment

const val PORT = 6868

interface EmbeddedServer {

    fun startServer()

    fun stopServer()
}