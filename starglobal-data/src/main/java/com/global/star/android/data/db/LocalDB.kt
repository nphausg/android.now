package com.global.star.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.global.star.android.data.entities.GithubUserEntities
import com.global.star.android.data.entities.GithubUserRemoteKeys

@Database(
    version = 1,
    exportSchema = false,
    entities = [GithubUserEntities::class, GithubUserRemoteKeys::class]
)
abstract class LocalDB : RoomDatabase() {
    abstract fun userDao(): GithubUserDao
    abstract fun userRemoteKeyDao(): GithubUserRemoteKeysDao
}