package com.global.star.android.screens.fragments

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.global.star.android.R
import com.global.star.android.databinding.FragmentMainBinding
import com.global.star.android.screens.adapters.GithubUsersAdapters
import com.global.star.android.shared.common.autoCleared
import com.global.star.android.shared.common.exceptions.SharedExceptions
import com.global.star.android.shared.common.extensions.*
import com.global.star.android.shared.libs.rxlivedata.applyFormValidator
import com.global.star.android.shared.libs.rxlivedata.observe
import com.global.star.android.shared.libs.rxlivedata.safeDispose
import com.global.star.android.shared.screens.fragments.BindingSharedFragment
import com.global.star.android.vm.MainViewModel
import com.jakewharton.rxbinding2.widget.afterTextChangeEvents
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainFragment : BindingSharedFragment<FragmentMainBinding>(R.layout.fragment_main) {

    companion object {
        const val EDIT_TEXT_STATE = "edit_text_state"
    }

    // region [Inject]
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }
    // endregion

    private var adapter by autoCleared<GithubUsersAdapters>()
    private var textChangeDispose: Disposable? = null
    private var isEditTextFocusState = false

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(EDIT_TEXT_STATE, isEditTextFocusState)
        super.onSaveInstanceState(outState)
    }

    override fun onSyncViews(savedInstanceState: Bundle?) {
        super.onSyncViews(savedInstanceState)
        // Previous
        isEditTextFocusState = savedInstanceState?.getBoolean(EDIT_TEXT_STATE) ?: false
        binding.toolbar.onLeftClickListener { viewModel.goBack() }
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
                    if (it !is SharedExceptions.NoNetworkConnection) {
                        showError(it.message)
                    }
                },
                { showLoading() })
        }
        adapter.addLoadStateListener { loadState ->
            val isEmpty = loadState.isLoading() && adapter.isEmpty()
            if (isEmpty) {
                binding.viewEmpty.show()
                binding.recyclerView.gone()
            } else {
                binding.viewEmpty.hide()
                binding.recyclerView.visible()
            }
        }

        binding.recyclerView.onItemClick { _, position ->
            viewModel.moveToUser(adapter.get(position))
        }

        addTextChanged()
    }

    override fun onDestroyView() {
        safeDisposeTextChanged()
        super.onDestroyView()
    }

    private fun addTextChanged() {
        if (!isEditTextFocusState) {
            isEditTextFocusState = true
            textChangeDispose = binding.editQuery.afterTextChangeEvents()
                .skipInitialValue()
                .compose(applyFormValidator())
                .subscribe({ event ->
                    val length = event.editable()?.length ?: 0
                    if (length > 1) {
                        viewModel.searchPagingUsers(event.editable().toString())
                    } else {
                        viewModel.loadRecentUsers()
                    }
                }, { it.printStackTrace() })
        }
    }

    private fun safeDisposeTextChanged() {
        textChangeDispose.safeDispose()
        isEditTextFocusState = false
    }

    override fun showLoading() {
        binding.progressBar.show()
    }

    override fun dismissLoading() {
        binding.progressBar.hide()
    }
}