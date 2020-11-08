package com.global.star.android.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxRemoteMediator
import com.global.star.android.data.api.UserNetworkService
import com.global.star.android.data.db.GithubUserDao
import com.global.star.android.data.db.GithubUserRemoteKeysDao
import com.global.star.android.data.db.LocalDB
import com.global.star.android.data.entities.GithubUserEntities
import com.global.star.android.data.entities.GithubUserRemoteKeys
import com.global.star.android.data.entities.GithubUserSearchResponse
import com.global.star.android.domain.entities.GithubUser
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class GithubUsersRxRemoteMediator(
    private val query: HashMap<String, Any>,
    private val networkService: UserNetworkService,
    private val userDao: GithubUserDao,
    private val remoteKeysDao: GithubUserRemoteKeysDao,
    private val database: LocalDB
) : RxRemoteMediator<Int, GithubUserEntities>() {

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, GithubUserEntities>
    ): Single<MediatorResult> {
        return Single.just(loadType)
            .subscribeOn(Schedulers.io())
            .map {
                when (it) {
                    LoadType.REFRESH -> {
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                        remoteKeys?.nextKey?.minus(1) ?: 1
                    }
                    LoadType.PREPEND -> {
                        val remoteKeys = getRemoteKeyForFirstItem(state)
                            ?: throw InvalidObjectException("Result is empty")
                        remoteKeys.prevKey ?: INVALID_PAGE
                    }
                    LoadType.APPEND -> {
                        val remoteKeys = getRemoteKeyForLastItem(state)
                            ?: throw InvalidObjectException("Result is empty")
                        remoteKeys.nextKey ?: INVALID_PAGE
                    }
                }
            }
            .flatMap { nextPage ->
                if (nextPage == INVALID_PAGE) {
                    Single.just(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    networkService.searchPagingUsers(
                        query = query,
                        page = nextPage,
                        pageSize = PER_PAGE
                    )
                        .map {
                            it.page = nextPage
                            insertToDb(nextPage, loadType, it)
                        }
                        .map<MediatorResult> { MediatorResult.Success(endOfPaginationReached = it.isEndOfPage()) }
                        .onErrorReturn { MediatorResult.Error(it) }
                }

            }
            .onErrorReturn { MediatorResult.Error(it) }
    }

    @Suppress("DEPRECATION")
    private fun insertToDb(
        page: Int,
        loadType: LoadType,
        data: GithubUserSearchResponse
    ): GithubUserSearchResponse {
        database.beginTransaction()
        try {
            if (loadType == LoadType.REFRESH) {
                remoteKeysDao.clearRemoteKeys()
                userDao.clearAll()
            }
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (data.isEndOfPage()) null else page + 1
            val keys = data.items?.map {
                GithubUserRemoteKeys(login = it.login, prevKey = prevKey, nextKey = nextKey)
            }
            remoteKeysDao.insertAll(keys)
            userDao.insertAll(data.items)
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
        }
        return data
    }

    private fun getRemoteKeyForLastItem(state: PagingState<Int, GithubUserEntities>): GithubUserRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let {
            remoteKeysDao.remoteKeysByLogin(it.login)
        }
    }

    private fun getRemoteKeyForFirstItem(state: PagingState<Int, GithubUserEntities>): GithubUserRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let {
            remoteKeysDao.remoteKeysByLogin(it.login)
        }
    }

    private fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, GithubUserEntities>): GithubUserRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.login?.let { id ->
                remoteKeysDao.remoteKeysByLogin(id)
            }
        }
    }

    companion object {
        const val INVALID_PAGE = -1
        const val PER_PAGE = 10
    }
}