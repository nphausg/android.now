package com.global.star.android

import com.global.star.android.shared.SharedApp
import com.global.star.android.shared.libs.logger.ReleaseLoggingTree
import com.global.star.android.di.DaggerAppComponent
import com.global.star.android.libs.CrashlyticsLoggingTree
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasAndroidInjector

class StarApp : SharedApp(), HasAndroidInjector {

    override fun releaseLoggingTree(): ReleaseLoggingTree {
        return CrashlyticsLoggingTree()
    }

    override fun isDebugMode(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}