package com.global.star.android.screens.activities

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.global.star.android.R
import com.global.star.android.databinding.ActivityProfileBinding
import com.global.star.android.domain.entities.GithubUser
import com.global.star.android.shared.libs.rxlivedata.observe
import com.global.star.android.shared.screens.activities.BindingSharedActivity
import com.global.star.android.vm.UserViewModel
import org.jetbrains.anko.browse
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class ProfileActivity : BindingSharedActivity<ActivityProfileBinding>(R.layout.activity_profile) {

    companion object {

        private const val KEY_ARGS = "KEY_ARGS"

        @JvmStatic
        fun start(context: Context, user: GithubUser?) {
            context.startActivity(
                context.intentFor<ProfileActivity>(
                    KEY_ARGS to user
                ).clearTop()
            )
        }

    }

    // region [Inject]
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: UserViewModel by viewModels { viewModelFactory }
    // endregion

    private var user: GithubUser? = null

    override fun onSyncViews(savedInstanceState: Bundle?) {
        super.onSyncViews(savedInstanceState)
        binding.toolbar.onLeftClickListener { onBackPressed() }
        binding.toolbar.onRightClickListener {
            browse(user?.htmlUrl ?: "")
        }
        user = intent.getParcelableExtra(KEY_ARGS)
        updateUI(user)
    }

    override fun onSyncEvents() {
        super.onSyncEvents()
        observe(viewModel.user) { updateUI(it) }
    }

    override fun onSyncData() {
        super.onSyncData()
        viewModel.getUser(user?.login)
    }

    private fun updateUI(user: GithubUser?) {
        binding.item = user
        binding.toolbar.setTitle(user?.login ?: "")
    }
}