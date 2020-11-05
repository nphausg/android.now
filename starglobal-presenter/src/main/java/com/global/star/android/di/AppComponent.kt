package com.global.star.android.di

import com.global.star.android.StarApp
import com.global.star.android.data.DataModule
import com.global.star.android.di.modules.AppModule
import com.global.star.android.shared.di.modules.SharedAppModule
import com.global.star.android.shared.di.scope.ApplicationScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        SharedAppModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<StarApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<StarApp>

}