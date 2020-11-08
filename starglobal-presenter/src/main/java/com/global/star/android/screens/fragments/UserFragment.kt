package com.global.star.android.screens.fragments

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.global.star.android.R
import com.global.star.android.databinding.FragmentUserBinding
import com.global.star.android.shared.libs.rxlivedata.observe
import com.global.star.android.shared.screens.fragments.BindingSharedFragment
import com.global.star.android.vm.MainViewModel
import com.global.star.android.vm.UserViewModel
import org.jetbrains.anko.support.v4.browse
import javax.inject.Inject

class UserFragment : BindingSharedFragment<FragmentUserBinding>(R.layout.fragment_user) {

    // region [Inject]
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }
    private val userViewModel: UserViewModel by viewModels { viewModelFactory }
    // endregion

    private val params by navArgs<UserFragmentArgs>()

    override fun onSyncViews(savedInstanceState: Bundle?) {
        super.onSyncViews(savedInstanceState)
        binding.item = params.user
        binding.toolbar.onLeftClickListener { viewModel.goBack() }
        binding.toolbar.onRightClickListener {
            browse(params.user?.htmlUrl ?: "")
        }
    }

    override fun onSyncEvents() {
        super.onSyncEvents()
        observe(userViewModel.user) { binding.item = it }
    }

    override fun onSyncData() {
        super.onSyncData()
        params.user?.let {
            binding.toolbar.setTitle(it.login)
            userViewModel.getUser(it.login)
        }
    }
}