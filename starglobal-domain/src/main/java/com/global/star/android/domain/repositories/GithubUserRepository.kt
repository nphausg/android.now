package com.global.star.android.domain.repositories

import androidx.paging.PagingData
import com.global.star.android.domain.entities.GithubUser
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Path

interface GithubUserRepository {

    fun searchPagingUsers(query: HashMap<String, Any>): Observable<PagingData<GithubUser>>

    fun getUser(username: String?): Single<GithubUser>

}