package com.global.star.android.screens.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.global.star.android.R
import com.global.star.android.databinding.ActivityMainBinding
import com.global.star.android.screens.fragments.MainFragmentDirections
import com.global.star.android.shared.common.extensions.navigateIfSafe
import com.global.star.android.shared.libs.imageloader.HandImageLoader
import com.global.star.android.shared.libs.rxlivedata.observe
import com.global.star.android.shared.screens.activities.BindingSharedActivity
import com.global.star.android.vm.MainUiEffect
import com.global.star.android.vm.MainViewModel
import javax.inject.Inject

class MainActivity : BindingSharedActivity<ActivityMainBinding>(R.layout.activity_main) {

    // region [Inject]
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }
    // endregion

    private lateinit var navController: NavController
    private lateinit var imageLoader: HandImageLoader

    override fun onSyncViews(savedInstanceState: Bundle?) {
        super.onSyncViews(savedInstanceState)
        imageLoader = HandImageLoader.getInstance(this)
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onSyncEvents() {
        super.onSyncEvents()
        observe(viewModel.uiState) { effect ->
            when (effect) {
                is MainUiEffect.GoUser -> {
                    navController.navigateIfSafe(MainFragmentDirections.actionMainToUser(effect.user))
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        imageLoader.flush()
    }

    override fun onDestroy() {
        super.onDestroy()
        imageLoader.close()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}