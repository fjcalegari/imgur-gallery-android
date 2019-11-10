package com.fjcalegari.imgur.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class GallerySearchResponse(

    @SerializedName("data")
    val data: List<Gallery>?

)

data class Gallery(

    @SerializedName("id")
    val id: String?,

    @SerializedName("cover")
    val cover: String?,

    @SerializedName("is_album")
    val isAlbum: Boolean

)