/*
 * Created by nphau on 4/1/23, 2:29 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 2:27 PM
 */

package com.nphausg.app.now.shared.internal.data.environment

import com.nphausg.app.now.shared.BuildConfig
import com.nphausg.app.now.shared.data.environment.EnvironmentProvider
import javax.inject.Inject

class EnvironmentProviderImpl @Inject constructor() : EnvironmentProvider {

    override val baseUrl: String = BuildConfig.BACKEND_URL

    override val apiKey: String = BuildConfig.API_KEY
}