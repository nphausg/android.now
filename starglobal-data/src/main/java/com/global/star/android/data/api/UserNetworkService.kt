package com.global.star.android.data.api

import com.global.star.android.data.entities.GithubUserEntities
import com.global.star.android.data.entities.GithubUserSearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface UserNetworkService {

    @GET("search/users")
    fun searchPagingUsers(
        @QueryMap query: HashMap<String, Any>,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Single<GithubUserSearchResponse>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String?): Observable<GithubUserEntities>
}