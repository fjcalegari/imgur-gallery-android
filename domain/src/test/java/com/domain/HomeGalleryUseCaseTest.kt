package com.domain

import com.domain.model.GalleryImageModel
import com.domain.repository.GallerySearchRepository
import com.domain.usecase.HomeGalleryUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeGalleryUseCaseTest {

    @Mock
    lateinit var gallerySearchRepository: GallerySearchRepository

    private lateinit var cut: HomeGalleryUseCase

    @Before
    fun setUp() {
        cut = HomeGalleryUseCase(gallerySearchRepository)
    }

    @Test
    fun `shows loading to start`() {
        Mockito.`when`(gallerySearchRepository.searchGalleries(any())).thenReturn(Single.just(mock()))

        cut.searchGalleries("cats").test().assertValueAt(0){
            it == HomeGalleryUseCase.Result.Loading
        }
    }

    @Test
    fun `returns the success result when success retrieving the galleries list`() {
        val galleriesList = arrayListOf<GalleryImageModel>()
        Mockito.`when`(gallerySearchRepository.searchGalleries(any())).thenReturn(Single.just(galleriesList))

        cut.searchGalleries("cats").test().assertValueAt(1){
            (it as HomeGalleryUseCase.Result.Success).items == galleriesList
        }
    }

    @Test
    fun `returns the failure result when error retrieving the galleries list`() {
        val error = Throwable()
        Mockito.`when`(gallerySearchRepository.searchGalleries(any())).thenReturn(Single.error(error))

        cut.searchGalleries("cats").test().assertValueAt(1){
            (it as HomeGalleryUseCase.Result.Failure).throwable == error
        }
    }

}