package com.global.star.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.global.star.android.data.entities.GithubUserEntities

@Database(
    version = 1,
    exportSchema = false,
    entities = [GithubUserEntities::class]
)
abstract class LocalDB : RoomDatabase() {
    abstract fun userDao(): GithubUserDao
}