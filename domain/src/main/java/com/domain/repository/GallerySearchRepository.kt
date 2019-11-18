package com.domain.repository

import com.domain.model.GalleryImageModel
import io.reactivex.Single

interface GallerySearchRepository {

    fun searchGalleries(q: String): Single<List<GalleryImageModel>>

}
