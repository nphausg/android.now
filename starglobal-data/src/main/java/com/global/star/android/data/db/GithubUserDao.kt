package com.global.star.android.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.global.star.android.data.entities.GithubUserEntities
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface GithubUserDao {

    /**
     * Insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GithubUserEntities): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<GithubUserEntities>?)

    /**
     * Find user by login
     */
    @Query("SELECT * FROM users WHERE login = :login")
    fun findByLogin(login: String?): Observable<GithubUserEntities>

    /**
     * Delete all users.
     */
    @Query("DELETE FROM users")
    fun clearAll()

}