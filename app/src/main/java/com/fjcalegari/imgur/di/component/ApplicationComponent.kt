package com.fjcalegari.imgur.di.component

import android.app.Application
import com.data.di.DataModule
import com.domain.di.DomainModule
import com.fjcalegari.imgur.ImgurApp
import com.fjcalegari.imgur.di.ApplicationModule
import com.fjcalegari.imgur.di.NetworkModule
import com.fjcalegari.imgur.di.builder.HomeActivityBuilder
import com.fjcalegari.imgur.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        DataModule::class,
        DomainModule::class,
        HomeActivityBuilder::class
    ]
)
interface ApplicationComponent : AndroidInjector<ImgurApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun dataModule(dataModule: DataModule): Builder
        fun domainModule(domainModule: DomainModule): Builder
        fun build(): ApplicationComponent
    }

    override fun inject(imgurApp: ImgurApp)
}
