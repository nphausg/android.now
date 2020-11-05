package com.global.star.android.libs

import com.global.star.android.shared.libs.logger.ReleaseLoggingTree

class CrashlyticsLoggingTree : ReleaseLoggingTree() {

    override fun applyKeys(priority: Int, tag: String?, message: String) {

    }

    override fun logException(throwable: Throwable?) {
    }

    override fun logOther(priority: Int, tag: String?, message: String) {

    }
}
