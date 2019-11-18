package com.data.source.remote

import com.data.model.GallerySearchDataModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurApi {

    @GET("gallery/search")
    fun searchGallery(@Query("q") q: String): Single<GallerySearchDataModel>

}
