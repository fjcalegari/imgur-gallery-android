package com.fjcalegari.imgur

import com.fjcalegari.imgur.di.ApplicationModule
import com.fjcalegari.imgur.di.NetworkModule
import com.fjcalegari.imgur.di.RepositoryModule
import com.fjcalegari.imgur.di.UseCaseModule
import com.fjcalegari.imgur.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class ImgurApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            //
        }

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .applicationModule(ApplicationModule(applicationContext))
            .networkModule(NetworkModule())
            .repositoryModule(RepositoryModule())
            .useCaseModule(UseCaseModule())
            .build()
    }

}