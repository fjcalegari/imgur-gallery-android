package com.fjcalegari.imgur.di

import com.fjcalegari.imgur.data.repositories.GalleryRepository
import com.fjcalegari.imgur.data.repositories.GalleryRepositoryImpl
import com.fjcalegari.imgur.data.source.remote.ImgurApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideGalleryRepository(imgurApi: ImgurApi): GalleryRepository {
        return GalleryRepositoryImpl(imgurApi)
    }

}