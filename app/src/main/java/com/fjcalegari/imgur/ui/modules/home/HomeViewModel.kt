package com.fjcalegari.imgur.ui.modules.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.fjcalegari.imgur.R
import com.fjcalegari.imgur.domain.model.GalleryModel
import com.fjcalegari.imgur.domain.usecases.GallerySearchUseCase
import com.fjcalegari.imgur.ui.common.Resource
import com.fjcalegari.imgur.ui.common.Status
import com.fjcalegari.imgur.ui.common.ext.AbsentLiveData
import com.fjcalegari.imgur.ui.common.ext.toLiveData
import com.fjcalegari.imgur.ui.common.ext.toResource
import com.fjcalegari.imgur.ui.modules.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val gallerySearchUseCase: GallerySearchUseCase
) : BaseViewModel() {

    private val _query = MutableLiveData<String>()

    val galleries: LiveData<Resource<List<GalleryModel>>> = Transformations
        .switchMap(_query) { search ->
            if (search.isNullOrBlank()) {
                AbsentLiveData.create()
            } else {
                gallerySearchUseCase.searchGalleries(search)
                    .toResource()
                    .toLiveData()
            }
        }

    val errorOrEmpty: LiveData<Resource<Int>> = Transformations
        .map(galleries) {
            if (it.status == Status.ERROR) {
                Resource.error("", R.string.error_message)
            } else if (it.status == Status.SUCCESS) {
                Resource.error("", R.string.empty_message)
            } else {
                Resource.error("", null)
            }
        }

    init {
        //mock
        _query.value = SEARCH_QUERY
    }

    fun refresh() {
        _query.value?.let {
            _query.value = it
        }
    }

    companion object {
        private const val SEARCH_QUERY = "cats"
    }

}