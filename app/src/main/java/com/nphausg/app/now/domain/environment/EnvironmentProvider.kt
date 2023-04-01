/*
 * Created by nphau on 4/1/23, 12:26 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 12:26 PM
 */

package com.nphausg.app.now.domain.environment

interface EnvironmentProvider {
    val baseUrl: String
    val apiKey: String
}