package com.domain.usecase

import com.domain.model.GalleryImageModel
import com.domain.repository.GallerySearchRepository
import com.domain.usecase.HomeGalleryUseCase.Result.*
import io.reactivex.Flowable
import javax.inject.Inject

class HomeGalleryUseCase @Inject constructor(
    private val galleryRepository: GallerySearchRepository
) {

    fun searchGalleries(q: String): Flowable<Result> {
        return galleryRepository.searchGalleries(q)
            .toFlowable()
            .map { Success(it) as Result }
            .onErrorReturn { Failure(it) }
            .startWith(Loading)
    }

    sealed class Result {
        object Loading : Result()
        data class Success(val items: List<GalleryImageModel>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

}
