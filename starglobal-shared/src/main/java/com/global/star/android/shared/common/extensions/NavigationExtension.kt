package com.global.star.android.shared.common.extensions

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun NavController.hasActionId(actionId: Int): Boolean {
    if (currentDestination == null)
        return false
    return currentDestination!!.getAction(actionId) != null
}

fun NavController.navigateIfSafe(navDirections: NavDirections) {
    if (hasActionId(navDirections.actionId))
        navigate(navDirections)
}

fun NavController.navigateIfSafe(
    @IdRes actionId: Int, vararg params: Pair<String, Any?>,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null
) {
    if (hasActionId(actionId))
        navigate(actionId, bundleOf(*params), navOptions, navExtras)
}