package com.fjcalegari.imgur.ui.modules.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domain.usecase.HomeGalleryUseCase
import com.domain.usecase.HomeGalleryUseCase.Result.*
import com.fjcalegari.imgur.R
import com.fjcalegari.imgur.base.executor.BaseSchedulerProvider
import com.fjcalegari.imgur.ui.modules.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeGalleryUseCase: HomeGalleryUseCase,
    private val schedulerProvider: BaseSchedulerProvider
) : BaseViewModel() {

    private val _viewState =
        MutableLiveData<ViewState>().apply { value = ViewState(isLoading = true) }
    val viewState: LiveData<ViewState>
        get() = _viewState

    fun loadImages() {
        homeGalleryUseCase.searchGalleries(SEARCH_QUERY)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe { handleSearchGalleriesResult(it) }
            .addTo(compositeDisposable)
    }

    // Handles Result from homeGalleryUseCase.searchGalleries
    private fun handleSearchGalleriesResult(result: HomeGalleryUseCase.Result) = when (result) {
        is Success -> if (result.items.isNotEmpty()) {
            _viewState.value =
                ViewState(items = result.items.toItemModelView())
        } else {
            _viewState.value =
                ViewState(
                    isErrorOrEmpty = true,
                    errorOrEmptyMessage = R.string.empty_message
                )
        }
        is Loading -> {
            _viewState.value =
                ViewState(isLoading = true)
        }
        is Failure -> {
            _viewState.value =
                ViewState(isErrorOrEmpty = true, errorOrEmptyMessage = R.string.error_message)
        }
    }

    fun refresh() {
        loadImages()
    }

    companion object {
        private const val SEARCH_QUERY = "cats"
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val isErrorOrEmpty: Boolean = false,
        val errorOrEmptyMessage: Int? = null,
        val items: List<ItemModelView> = listOf()
    )

}
