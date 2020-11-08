package com.global.star.android.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUserSearchResponse(
    @SerializedName("total_count")
    val total: Int = 0,
    var page: Int = 0,
    @SerializedName("items")
    val items: List<GithubUserEntities>? = listOf()
) : Parcelable {

    fun isEndOfPage(): Boolean {
        return total == page
    }
}