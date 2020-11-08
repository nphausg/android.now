package com.global.star.android.vm

import com.global.star.android.domain.entities.GithubUser

sealed class MainUiEffect {
    object GoBack : MainUiEffect()
    data class GoUser(val user: GithubUser? = null) : MainUiEffect()
}