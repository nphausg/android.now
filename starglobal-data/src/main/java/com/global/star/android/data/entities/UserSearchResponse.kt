package com.global.star.android.data.entities

import com.google.gson.annotations.SerializedName

data class UserSearchResponse(
    @SerializedName("total_count")
    val total: Int = 0,
    @SerializedName("items")
    val items: List<GithubUserEntities>? = listOf()
)