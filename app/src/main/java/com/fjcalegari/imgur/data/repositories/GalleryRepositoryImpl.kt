package com.fjcalegari.imgur.data.repositories

import com.fjcalegari.imgur.data.mapper.toGalleries
import com.fjcalegari.imgur.data.source.remote.ImgurApi
import com.fjcalegari.imgur.domain.model.GalleryModel
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryRepositoryImpl @Inject constructor(
    private val imgurApi: ImgurApi
) : GalleryRepository {

    override fun searchGalleries(q: String): Flowable<List<GalleryModel>> =
        imgurApi.searchGallery(q)
            .map { it.data?.toGalleries() ?: listOf() }
            .toFlowable()

}