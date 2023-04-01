/*
 * Created by nphau on 4/1/23, 2:19 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 2:19 PM
 */

package com.nphausg.app.now.network

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.nphausg.app.now.network.test", appContext.packageName)
    }
}