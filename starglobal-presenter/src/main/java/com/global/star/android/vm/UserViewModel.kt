package com.global.star.android.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.global.star.android.domain.entities.GithubUser
import com.global.star.android.domain.usecases.GetUserByUserNameUseCase
import com.global.star.android.shared.common.extensions.SingleLiveEvent
import com.global.star.android.shared.libs.rxlivedata.addTo
import com.global.star.android.shared.libs.rxlivedata.applyObservableIoScheduler
import com.global.star.android.shared.vm.SharedViewModel
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getUserByUserNameUseCase: GetUserByUserNameUseCase
) : SharedViewModel() {

    private val _user: MutableLiveData<GithubUser> = SingleLiveEvent()
    var user: LiveData<GithubUser> = _user

    fun getUser(userName: String?) {
        getUserByUserNameUseCase.execute(userName)
            .compose(applyObservableIoScheduler())
            .subscribe(
                { _user.postValue(it) },
                { it.printStackTrace() })
            .addTo(getCompositeDisposable())
    }
}