package com.global.star.android.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubUser(
    override val avatarUrl: String? = "",
    override val htmlUrl: String? = "",
    override val bio: String? = "",
    override val blog: String? = "",
    override val company: String? = "",
    override val createdAt: String? = "",
    override val location: String? = "",
    override val email: String? = "",
    override val followers: Int,
    override val following: Int,
    override val login: String? = "",
    override val name: String? = "",
    override val id: Long,
) : Parcelable, IGithubUserModel