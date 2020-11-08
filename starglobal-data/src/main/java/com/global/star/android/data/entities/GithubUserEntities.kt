package com.global.star.android.data.entities

import android.os.Parcelable
import com.global.star.android.domain.entities.IGithubUserModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUserEntities(
    @field:SerializedName("avatar_url")
    override val avatarUrl: String?,
    @field:SerializedName("html_url")
    override val htmlUrl: String?,
    @field:SerializedName("bio")
    override val bio: String?,
    @field:SerializedName("blog")
    override val blog: String?,
    @field:SerializedName("company")
    override val company: String?,
    @field:SerializedName("createdAt")
    override val createdAt: String?,
    @field:SerializedName("location")
    override val location: String?,
    @field:SerializedName("email")
    override val email: String?,
    @field:SerializedName("followers")
    override val followers: Int,
    @field:SerializedName("following")
    override val following: Int,
    @field:SerializedName("login")
    override val login: String?,
    @field:SerializedName("name")
    override val name: String?
) : IGithubUserModel, Parcelable