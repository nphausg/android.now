package com.global.star.android.data.api

import com.global.star.android.data.NetworkEndpointCoordinator
import com.global.star.android.data.common.HeaderType
import com.global.star.android.shared.common.Singleton
import okhttp3.Interceptor
import javax.inject.Inject
import javax.inject.Named

class UserNetworkModule @Inject constructor() : SharedNetworkModule() {

    @Inject
    @Named(HeaderType.LOGGER)
    lateinit var loggerInterceptor: Interceptor

    @Inject
    @Named(HeaderType.UN_AUTHORIZATION)
    lateinit var unAuthorizationInterceptor: Interceptor

    companion object : Singleton<UserNetworkModule>(::UserNetworkModule)

    fun userService() = getService<UserNetworkService>()

    override fun interceptors(): List<Interceptor> {
        return listOf(loggerInterceptor, unAuthorizationInterceptor)
    }

    override fun getBaseUrl(): String {
        return NetworkEndpointCoordinator.BASE_URL
    }

}