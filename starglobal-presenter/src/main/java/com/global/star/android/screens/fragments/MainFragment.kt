package com.global.star.android.screens.fragments

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.global.star.android.R
import com.global.star.android.databinding.FragmentMainBinding
import com.global.star.android.screens.adapters.GithubUsersAdapters
import com.global.star.android.shared.common.autoCleared
import com.global.star.android.shared.common.extensions.gone
import com.global.star.android.shared.common.extensions.isError
import com.global.star.android.shared.common.extensions.isLoading
import com.global.star.android.shared.common.extensions.visible
import com.global.star.android.shared.libs.rxlivedata.observe
import com.global.star.android.shared.screens.fragments.BindingSharedFragment
import com.global.star.android.vm.MainViewModel
import javax.inject.Inject

class MainFragment : BindingSharedFragment<FragmentMainBinding>(R.layout.fragment_main) {

    // region [Inject]
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }
    // endregion

    private var adapter by autoCleared<GithubUsersAdapters>()

    override fun onSyncViews(savedInstanceState: Bundle?) {
        super.onSyncViews(savedInstanceState)
        adapter = GithubUsersAdapters()
        binding.recyclerView.adapter = adapter
    }

    override fun onSyncEvents() {
        super.onSyncEvents()
        observe(viewModel.users) { usersResponse ->
            usersResponse.fold(
                {
                    dismissLoading()
                    adapter.submitData(lifecycle, it)
                },
                {
                    dismissLoading()
                    showError(it.message)
                },
                { showLoading() })
        }
        adapter.addLoadStateListener { loadState ->
            val isError = loadState.isError()
            val isEmpty = loadState.isLoading() && adapter.isEmpty()
            if (isEmpty || isError) {
                // binding.viewEmpty.show()
                binding.recyclerView.gone()
            } else {
                // binding.viewEmpty.hide()
                binding.recyclerView.visible()
            }
        }
    }

    override fun onSyncData() {
        super.onSyncData()
        viewModel.searchPagingUsers("g")
    }

    override fun showLoading() {
        binding.progressBar.show()
    }

    override fun dismissLoading() {
        binding.progressBar.hide()
    }
}