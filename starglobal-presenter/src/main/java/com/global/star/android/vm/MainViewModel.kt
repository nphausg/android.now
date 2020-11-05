package com.global.star.android.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.global.star.android.domain.entities.GithubUser
import com.global.star.android.domain.usecases.SearchUsersUseCase
import com.global.star.android.shared.common.functional.Result
import com.global.star.android.shared.libs.rxlivedata.addTo
import com.global.star.android.shared.vm.SharedViewModel
import javax.inject.Inject

typealias UsersResponse = Result<PagingData<GithubUser>, Throwable>

class MainViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase
) : SharedViewModel() {

    private val _users: MutableLiveData<UsersResponse> = MutableLiveData()
    var users: LiveData<UsersResponse> = _users

    fun searchPagingUsers(query: String) {
        searchUsersUseCase.execute(hashMapOf("q" to query))
            .doOnSubscribe { _users.postValue(Result.Loading) }
            .cachedIn(viewModelScope)
            .subscribe(
                { _users.postValue(Result.Success(it)) },
                { _users.postValue(Result.Failure(it)) })
            .addTo(getCompositeDisposable())
    }

}