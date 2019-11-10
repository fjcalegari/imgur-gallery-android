package com.fjcalegari.imgur.di.builder

import com.fjcalegari.imgur.di.module.HomeModule
import com.fjcalegari.imgur.ui.modules.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module interface HomeActivityBuilder {
    @ContributesAndroidInjector(modules = [HomeModule::class])
    fun contributeHomeActivity(): HomeActivity
}