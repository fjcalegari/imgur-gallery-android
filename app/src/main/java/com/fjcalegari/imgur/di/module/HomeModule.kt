package com.fjcalegari.imgur.di.module

import androidx.lifecycle.ViewModel
import com.fjcalegari.imgur.di.ViewModelKey
import com.fjcalegari.imgur.ui.modules.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

}