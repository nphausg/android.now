package com.global.star.android.shared.di.modules

import androidx.lifecycle.ViewModelProvider
import com.global.star.android.shared.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface SharedViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
