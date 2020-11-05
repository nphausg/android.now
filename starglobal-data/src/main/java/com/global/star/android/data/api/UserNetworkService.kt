package com.global.star.android.data.api

import com.global.star.android.data.entities.UserSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface UserNetworkService {

    @GET("search/users")
    suspend fun searchPagingUsers(
        @QueryMap query: HashMap<String, Any>,
        @Query("page") page: Int,
        @Query("per_page") size: Int
    ): UserSearchResponse

}