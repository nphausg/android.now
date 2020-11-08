package com.global.star.android.domain.usecases

import com.global.star.android.domain.common.ObservableUseCase
import com.global.star.android.domain.entities.GithubUser
import com.global.star.android.domain.repositories.GithubUserRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetUserByUserNameUseCase @Inject constructor(private val repository: GithubUserRepository) :
    ObservableUseCase<String?, GithubUser>() {

    override fun preExecute(params: String?): Observable<GithubUser> {
        return repository.getUser(params)
    }
}