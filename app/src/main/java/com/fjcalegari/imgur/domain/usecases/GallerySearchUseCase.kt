package com.fjcalegari.imgur.domain.usecases

import com.fjcalegari.imgur.data.repositories.GalleryRepository
import com.fjcalegari.imgur.domain.executor.BaseSchedulerProvider
import com.fjcalegari.imgur.domain.model.GalleryModel
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GallerySearchUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    fun searchGalleries(q: String): Flowable<List<GalleryModel>> {
        return galleryRepository.searchGalleries(q)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

}