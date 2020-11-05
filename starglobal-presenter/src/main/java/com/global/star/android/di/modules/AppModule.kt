package com.global.star.android.di.modules

import androidx.lifecycle.ViewModel
import com.global.star.android.screens.activities.MainActivity
import com.global.star.android.screens.fragments.MainFragment
import com.global.star.android.shared.di.ViewModelKey
import com.global.star.android.vm.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface AppModule {

    // region [ViewModel]
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
    // endregion

    // region [Activity]
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity
    // endregion

    // region [Fragment]
    @ContributesAndroidInjector
    fun contributeMainFragment(): MainFragment
    // endregion

}