package com.global.star.android.screens.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.global.star.android.R
import com.global.star.android.databinding.ActivityMainBinding
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
        setUpNavigation()
    }

    override fun onSyncEvents() {
        super.onSyncEvents()
        observe(viewModel.uiState) { effect ->
            when (effect) {
                is MainUiEffect.GoUser -> {
                    ProfileActivity.start(this, effect.user)
                }
                else -> onBackPressed()
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

    private fun setUpNavigation() {
        navController = findNavController(R.id.graph_main)
    }
}