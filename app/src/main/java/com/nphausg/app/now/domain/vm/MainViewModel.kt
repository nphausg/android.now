/*
 * Created by nphau on 4/1/23, 12:46 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 12:46 PM
 */

package com.nphausg.app.now.domain.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nphausg.app.now.domain.usecase.EnvironmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val environmentUseCase: EnvironmentUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MainUIState())
        private set

    fun startServer() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            environmentUseCase.startServer()
            delay(1000)
            uiState = uiState.copy(isLoading = false)
        }
    }

    fun stopServer() {
        environmentUseCase.stopServer()
    }
}

data class MainUIState(
    val isLoading: Boolean = false
)