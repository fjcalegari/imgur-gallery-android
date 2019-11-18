package com.data.model

import com.domain.model.GalleryImageModel
import com.google.gson.annotations.SerializedName

data class GallerySearchDataModel(

    @SerializedName("data")
    val data: List<GallerySearchItemDataModel>? = emptyList()

)

data class GallerySearchItemDataModel(

    @SerializedName("id")
    val id: String? = "",

    @SerializedName("cover")
    val cover: String? = "",

    @SerializedName("is_album")
    val isAlbum: Boolean

)

internal fun List<GallerySearchItemDataModel>.toGalleryImageModel(): List<GalleryImageModel> =
    this.map {
        GalleryImageModel(
            id = it.id ?: "",
            cover = it.cover ?: "",
            isAlbum = it.isAlbum
        )
    }
