package com.global.star.android.data.api

import com.global.star.android.data.entities.GithubUserEntities
import com.global.star.android.data.entities.UserSearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface UserNetworkService {

    @GET("search/users")
    suspend fun searchPagingUsers(
        @QueryMap query: HashMap<String, Any>,
        @Query("page") page: Int,
        @Query("per_page") size: Int
    ): UserSearchResponse

    @GET("users/{username}")
    fun getUser(@Path("username") username: String?): Observable<GithubUserEntities>
}