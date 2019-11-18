package com.fjcalegari.imgur.di

import android.content.Context
import com.fjcalegari.imgur.base.executor.BaseSchedulerProvider
import com.fjcalegari.imgur.base.executor.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context = context

    @Singleton
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

}
