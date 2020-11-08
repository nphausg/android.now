package com.global.star.android.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.global.star.android.data.entities.GithubUserRemoteKeys

@Dao
interface GithubUserRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKey: List<GithubUserRemoteKeys>?)

    @Query("SELECT * FROM users_remote_keys WHERE login = :login")
    fun remoteKeysByLogin(login: String?): GithubUserRemoteKeys?

    @Query("DELETE FROM users_remote_keys")
    fun clearRemoteKeys()

}