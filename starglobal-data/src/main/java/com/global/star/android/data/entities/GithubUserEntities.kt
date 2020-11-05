package com.global.star.android.data.entities

import android.os.Parcelable
import com.global.star.android.domain.entities.IGithubUserModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUserEntities(
    @field:SerializedName("login")
    override val userName: String?,
    @field:SerializedName("avatar_url")
    override val avatarUrl: String?
) : IGithubUserModel, Parcelable