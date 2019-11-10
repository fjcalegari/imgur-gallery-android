package com.fjcalegari.imgur.data.source.remote

import com.fjcalegari.imgur.data.source.remote.model.GallerySearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurApi {

    @GET("gallery/search")
    fun searchGallery(@Query("q") q: String): Single<GallerySearchResponse>

}