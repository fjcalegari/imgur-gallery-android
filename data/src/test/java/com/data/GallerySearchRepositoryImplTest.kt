package com.data

import com.data.model.GallerySearchDataModel
import com.data.model.GallerySearchItemDataModel
import com.data.repository.GallerySearchRepositoryImpl
import com.data.source.remote.ImgurApi
import com.domain.model.GalleryImageModel
import com.domain.repository.GallerySearchRepository
import com.nhaarman.mockitokotlin2.any
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GallerySearchRepositoryImplTest {

    @Mock
    lateinit var imgurApi: ImgurApi
    private lateinit var cut: GallerySearchRepository

    @Before
    fun setUp() {
        cut = GallerySearchRepositoryImpl(imgurApi)
    }

    @Test
    fun `searchGalleries fetches GallerySearchDataModel and maps to DomainModel`() {
        Mockito.`when`(imgurApi.searchGallery(any())).thenReturn(Single.just(responseModel))
        cut.searchGalleries("cats").test().assertValue(resultModel)
    }

    @Test
    fun `searchGalleries returns emptyList if response is empty`() {
        Mockito.`when`(imgurApi.searchGallery(any())).thenReturn(Single.just(GallerySearchDataModel()))
        cut.searchGalleries("cats").test().assertValue(emptyList())
    }

    @Test
    fun `searchGalleries returns error on failure`() {
        val error = Throwable()
        Mockito.`when`(imgurApi.searchGallery(any())).thenReturn(Single.error(error))
        cut.searchGalleries("cats").test().assertError(error)
    }

    private val responseModel = GallerySearchDataModel(
        data = listOf(
            GallerySearchItemDataModel(cover =  "1", isAlbum = true),
            GallerySearchItemDataModel(id = "2",  isAlbum = false)
        )
    )

    private val resultModel = listOf(
        GalleryImageModel("", "1", true),
        GalleryImageModel("2", "", false)
    )

}