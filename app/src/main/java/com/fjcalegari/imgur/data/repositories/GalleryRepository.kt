package com.fjcalegari.imgur.data.repositories

import com.fjcalegari.imgur.domain.model.GalleryModel
import io.reactivex.Flowable

interface GalleryRepository {

    fun searchGalleries(q: String): Flowable<List<GalleryModel>>

}