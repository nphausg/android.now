package com.global.star.android.domain.usecases

import androidx.paging.PagingData
import com.global.star.android.domain.common.ObservableUseCase
import com.global.star.android.domain.entities.GithubUser
import com.global.star.android.domain.repositories.GithubUserRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(private val repository: GithubUserRepository) :
    ObservableUseCase<HashMap<String, Any>, PagingData<GithubUser>>() {
    override fun preExecute(params: HashMap<String, Any>): Observable<PagingData<GithubUser>> {
        return repository.searchPagingUsers(params)
    }

}