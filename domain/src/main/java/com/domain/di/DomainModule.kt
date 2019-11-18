package com.domain.di

import com.domain.repository.GallerySearchRepository
import com.domain.usecase.HomeGalleryUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideHomeGalleryUseCase(
        gallerySearchRepository: GallerySearchRepository
    ): HomeGalleryUseCase {
        return HomeGalleryUseCase(gallerySearchRepository)
    }

}
