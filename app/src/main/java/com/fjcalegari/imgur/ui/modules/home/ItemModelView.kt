package com.fjcalegari.imgur.ui.modules.home

import com.domain.model.GalleryImageModel

private const val BASE_URL_IMG = "https://i.imgur.com/"
private const val BASE_IMG_SIZE_EXT = "t.jpg"

data class ItemModelView(
        val id: String,
        val urlToImage: String
)

internal fun List<GalleryImageModel>.toItemModelView(): List<ItemModelView> =
        this.map {
            ItemModelView(
                    id = it.id,
                    urlToImage =
                    BASE_URL_IMG.plus(
                            when (it.isAlbum) {
                                true -> it.cover
                                false -> it.id
                            }
                    ).plus(BASE_IMG_SIZE_EXT)
            )
        }
