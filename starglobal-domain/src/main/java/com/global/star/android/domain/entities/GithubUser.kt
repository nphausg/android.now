package com.global.star.android.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubUser(
    override val userName: String? = "",
    override val avatarUrl: String? = "",
) : Parcelable, IGithubUserModel