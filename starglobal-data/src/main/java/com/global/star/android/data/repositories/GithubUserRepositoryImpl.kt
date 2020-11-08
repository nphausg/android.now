package com.global.star.android.data.repositories

import androidx.paging.PagingData
import androidx.paging.rxjava2.observable
import com.global.star.android.data.api.UserNetworkService
import com.global.star.android.data.db.GithubUserDao
import com.global.star.android.data.paging.GithubUsersPagingSource
import com.global.star.android.data.transform.EntitiesTransformer
import com.global.star.android.domain.entities.GithubUser
import com.global.star.android.domain.repositories.GithubUserRepository
import com.global.star.android.shared.common.exceptions.SharedExceptions
import com.global.star.android.shared.libs.network.NetworkHandler
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val userService: UserNetworkService,
    private val userDao: GithubUserDao,
) : SharedRepository(), GithubUserRepository {

    override fun searchPagingUsers(query: HashMap<String, Any>): Observable<PagingData<GithubUser>> {
        return if (networkHandler.isNetworkAvailable())
            createPagerConfig(GithubUsersPagingSource(query, userService)).observable
        else
            Observable.error(SharedExceptions.NoNetworkConnection)
    }


    /**
     * If load data from remote got error with any problem, then push data from localDB
     * Else -> save data to local if remote request successful
     */
    override fun getUser(username: String?): Observable<GithubUser> {
        val findByLoginInDB = userDao.findByLogin(username).subscribeOn(Schedulers.io())
        return userService.getUser(username)
            .flatMap {
                userDao.insert(it)
                    .subscribeOn(Schedulers.io())
                    .andThen(findByLoginInDB)
            }
            .onErrorResumeNext(findByLoginInDB)
            .map { EntitiesTransformer.fromGithubUserEntitiesToGithubUser(it) }
    }

}