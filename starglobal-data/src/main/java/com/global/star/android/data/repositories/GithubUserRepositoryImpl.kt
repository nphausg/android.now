package com.global.star.android.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava2.observable
import com.global.star.android.data.api.UserNetworkService
import com.global.star.android.data.db.GithubUserDao
import com.global.star.android.data.db.GithubUserRemoteKeysDao
import com.global.star.android.data.db.LocalDB
import com.global.star.android.data.paging.GithubUsersRxRemoteMediator
import com.global.star.android.data.transform.EntitiesTransformer
import com.global.star.android.domain.entities.GithubUser
import com.global.star.android.domain.repositories.GithubUserRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor(
    private val networkService: UserNetworkService,
    private val userDao: GithubUserDao,
    private val remoteKeysDao: GithubUserRemoteKeysDao,
    private val database: LocalDB
) : SharedRepository(), GithubUserRepository {

    override fun searchPagingUsers(query: HashMap<String, Any>): Observable<PagingData<GithubUser>> {
        return Pager(
            config = getPagingConfig(),
            remoteMediator = GithubUsersRxRemoteMediator(
                query,
                networkService,
                userDao,
                remoteKeysDao,
                database
            ),
            pagingSourceFactory = { database.userDao().getAll() }
        ).observable.map { paging ->
            paging.map { EntitiesTransformer.fromGithubUserEntitiesToGithubUser(it) }
        }
        // createPagerConfig(GithubUsersPagingSource(query, networkService)).observable
    }

    /**
     * If load data from remote got error with any problem, then push data from localDB
     * Else -> save data to local if remote request successful
     */
    override fun getUser(username: String?): Observable<GithubUser> {
        val findByLoginInDB = userDao.findByLogin(username).subscribeOn(Schedulers.io())
        return networkService.getUser(username)
            .flatMap {
                userDao.insert(it)
                    .subscribeOn(Schedulers.io())
                    .andThen(findByLoginInDB)
            }
            .onErrorResumeNext(findByLoginInDB)
            .map { EntitiesTransformer.fromGithubUserEntitiesToGithubUser(it) }
    }

}