package com.fjcalegari.imgur.data.mapper

import com.fjcalegari.imgur.data.source.remote.model.Gallery
import com.fjcalegari.imgur.domain.model.GalleryModel

private const val BASE_URL_IMG = "https://i.imgur.com/"
private const val BASE_IMG_SIZE_EXT  = "t.jpg"

fun List<Gallery>.toGalleries(): List<GalleryModel> =
    this.map {
        GalleryModel(
            id = it.id ?: "",
            urlToImage =
            BASE_URL_IMG.plus(
                when(it.isAlbum) {
                    true -> it.cover ?: ""
                    false -> it.id ?: ""
                }
            ).plus(BASE_IMG_SIZE_EXT)
        )
    }