package com.global.star.android.data.paging

import com.global.star.android.data.api.UserNetworkService
import com.global.star.android.data.common.CorePagingSource
import com.global.star.android.domain.entities.GithubUser

class GithubUsersPagingSource(
    private val query: HashMap<String, Any>,
    private val service: UserNetworkService
) : CorePagingSource<Int, GithubUser>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUser> {
        val currentPageIndex = params.key ?: beginPageIndex()
        return try {
            val response = service.searchPagingUsers(query, currentPageIndex, params.loadSize)
            val items = response.items ?: listOf()
            result(currentPageIndex, items.map {
                GithubUser(userName = it.userName, avatarUrl = it.avatarUrl)
            })
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun beginPageIndex(): Int = 0
}

