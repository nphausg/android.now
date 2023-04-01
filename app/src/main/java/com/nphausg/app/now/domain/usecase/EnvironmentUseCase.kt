/*
 * Created by nphau on 4/1/23, 1:04 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 1:04 PM
 */

package com.nphausg.app.now.domain.usecase

import com.nphausg.app.now.internal.repository.EnvironmentRepository
import javax.inject.Inject

interface EnvironmentUseCase {
    fun startServer()

    fun stopServer()
}

class EnvironmentUseCaseImpl @Inject constructor(
    private val repository: EnvironmentRepository
) : EnvironmentUseCase {

    override fun startServer() {
        repository.startServer()
    }

    override fun stopServer() {
        repository.stopServer()
    }

}