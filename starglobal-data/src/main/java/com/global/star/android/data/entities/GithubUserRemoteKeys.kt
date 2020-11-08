package com.global.star.android.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users_remote_keys")
data class GithubUserRemoteKeys(
    @PrimaryKey val login: String,
    val prevKey: Int?,
    val nextKey: Int?
) : Parcelable