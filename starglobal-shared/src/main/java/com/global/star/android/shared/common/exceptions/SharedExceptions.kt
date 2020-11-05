package com.global.star.android.shared.common.exceptions

sealed class SharedExceptions(message: String? = "") : RuntimeException(message) {
    object NoNetworkConnection : SharedExceptions()
}
