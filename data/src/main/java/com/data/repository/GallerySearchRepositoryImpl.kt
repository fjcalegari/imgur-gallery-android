package com.data.repository

import com.data.model.toGalleryImageModel
import com.data.source.remote.ImgurApi
import com.domain.model.GalleryImageModel
import com.domain.repository.GallerySearchRepository
import io.reactivex.Single
import javax.inject.Inject

class GallerySearchRepositoryImpl @Inject constructor(
    private val imgurApi: ImgurApi
) : GallerySearchRepository {

    override fun searchGalleries(q: String): Single<List<GalleryImageModel>> =
        imgurApi.searchGallery(q)
            .map { it.data?.toGalleryImageModel() ?: listOf() }

}
