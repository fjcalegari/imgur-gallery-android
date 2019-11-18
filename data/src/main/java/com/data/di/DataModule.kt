package com.data.di

import com.data.repository.GallerySearchRepositoryImpl
import com.data.source.remote.ImgurApi
import com.domain.repository.GallerySearchRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideImgurApi(retrofit: Retrofit): ImgurApi = retrofit.create(ImgurApi::class.java)

    @Provides
    fun provideGallerySearchRepository(imgurApi: ImgurApi): GallerySearchRepository {
        return GallerySearchRepositoryImpl(imgurApi)
    }

}
