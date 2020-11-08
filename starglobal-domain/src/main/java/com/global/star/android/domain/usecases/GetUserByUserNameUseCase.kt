package com.global.star.android.domain.usecases

import com.global.star.android.domain.common.SingleUseCase
import com.global.star.android.domain.entities.GithubUser
import com.global.star.android.domain.repositories.GithubUserRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUserByUserNameUseCase @Inject constructor(private val repository: GithubUserRepository) :
    SingleUseCase<String?, GithubUser>() {

    override fun preExecute(params: String?): Single<GithubUser> {
        return repository.getUser(params)
    }
}