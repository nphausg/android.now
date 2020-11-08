package com.global.star.android.domain.repositories

import androidx.paging.PagingData
import com.global.star.android.domain.entities.GithubUser
import io.reactivex.Observable

interface GithubUserRepository {

    fun searchPagingUsers(query: HashMap<String, Any>): Observable<PagingData<GithubUser>>

    fun getUser(username: String?): Observable<GithubUser>

}