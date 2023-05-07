/*
 * Created by nphau on 4/1/23, 11:24 AM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 11:24 AM
 */

package com.nphausg.app.now

import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NowApp : Application(), DefaultLifecycleObserver {

    override fun onCreate() {
        super<Application>.onCreate()
    }

}