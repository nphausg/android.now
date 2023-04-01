/*
 * Created by nphau on 11/19/22, 4:16 PM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 11/19/22, 3:58 PM
 */

package com.nphausg.app.now.activities

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nphausg.app.now.R
import com.nphausg.app.now.domain.environment.PORT
import com.nphausg.app.now.domain.vm.MainViewModel
import com.nphausg.app.now.extensions.animateFlash
import com.nphausg.app.now.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Keep the splash screen on-screen until the UI state is loaded. This condition is
        // evaluated each time the app needs to be redrawn so it should be fast to avoid blocking
        // the UI.
        splashScreen.setKeepOnScreenCondition {
            viewModel.uiState.isLoading
        }

        findViewById<AppCompatImageView>(R.id.image_logo).animateFlash()

        // Show IP Address
        findViewById<TextView>(R.id.text_status).apply {
            val simpleTextApi = String.format("GET: %s:%d", NetworkUtils.getLocalIpAddress(), PORT)
            val apiGet = String.format("GET: %s:%d/fruits", NetworkUtils.getLocalIpAddress(), PORT)
            val apiGetWithId =
                String.format("GET: %s:%d/fruits/{id}", NetworkUtils.getLocalIpAddress(), PORT)
            text = String.format("%s\n%s\n%s", simpleTextApi, apiGet, apiGetWithId)
        }

        // Start server
        viewModel.startServer()
    }

    override fun onDestroy() {
        viewModel.stopServer()
        super.onDestroy()
    }

}