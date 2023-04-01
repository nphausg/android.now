/*
 * Created by nphau on 4/1/23, 12:26 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 12:26 PM
 */

package com.nphausg.app.now.internal.environment

import com.nphausg.app.now.BuildConfig
import com.nphausg.app.now.domain.environment.EnvironmentProvider
import javax.inject.Inject

internal class EnvironmentProviderImpl @Inject constructor() : EnvironmentProvider {

    override val baseUrl: String = BuildConfig.BACKEND_URL

    override val apiKey: String = BuildConfig.API_KEY
}