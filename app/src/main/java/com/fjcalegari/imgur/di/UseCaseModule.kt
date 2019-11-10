package com.fjcalegari.imgur.di

import com.fjcalegari.imgur.data.repositories.GalleryRepository
import com.fjcalegari.imgur.domain.executor.BaseSchedulerProvider
import com.fjcalegari.imgur.domain.executor.SchedulerProvider
import com.fjcalegari.imgur.domain.usecases.GallerySearchUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

    @Singleton
    @Provides
    fun provideGallerySearchUseCase(galleryRepository: GalleryRepository, schedulerProvider: BaseSchedulerProvider): GallerySearchUseCase {
        return GallerySearchUseCase(galleryRepository, schedulerProvider)
    }

}