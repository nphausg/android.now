package com.global.star.android.screens.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.global.star.android.R
import com.global.star.android.databinding.ActivityMainBinding
import com.global.star.android.shared.libs.imageloader.ImageLoader
import com.global.star.android.shared.screens.activities.BindingSharedActivity
import com.global.star.android.vm.MainViewModel
import javax.inject.Inject

class MainActivity : BindingSharedActivity<ActivityMainBinding>(R.layout.activity_main) {

    // region [Inject]
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }
    // endregion

    private lateinit var navController: NavController
    private lateinit var imageLoader: ImageLoader

    override fun onSyncViews(savedInstanceState: Bundle?) {
        super.onSyncViews(savedInstanceState)
        imageLoader = ImageLoader.getInstance(this)
        navController = findNavController(R.id.nav_host_fragment)
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