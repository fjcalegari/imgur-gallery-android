package com.fjcalegari.imgur.ui.common.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.fjcalegari.imgur.ui.common.Resource
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import timber.log.Timber

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>

fun <T> Flowable<T>.toResource(): Flowable<Resource<T>> {
    return compose { item ->
        item
            .map { Resource.success(it) }
            .startWith(Resource.loading(null))
            .onErrorReturn {
                    e -> Timber.e(e)
                    Resource.error(e.message ?: "Unknown Error", null)
            }
    }
}

fun defaultErrorHandler(): (Throwable) -> Unit = { e -> Timber.e(e) }