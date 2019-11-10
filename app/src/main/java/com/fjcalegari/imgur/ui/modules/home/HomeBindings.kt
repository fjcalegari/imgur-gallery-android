package com.fjcalegari.imgur.ui.modules.home

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fjcalegari.imgur.domain.model.GalleryModel
import com.fjcalegari.imgur.ui.view.ScrollChildSwipeRefreshLayout

object HomeBindings {

    @BindingAdapter("app:items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, items: List<GalleryModel>?) {
        items?.let {
            recyclerView.visibility = if (items.isNotEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
            with(recyclerView.adapter as HomeGalleryAdapter) {
                submitList(items)
            }
        } ?: also { recyclerView.visibility = View.GONE }
    }

    /**
     * Reloads the data when the pull-to-refresh is triggered.
     *
     * Creates the `android:onRefresh` for a [SwipeRefreshLayout].
     */
    @BindingAdapter("android:onRefresh")
    @JvmStatic fun ScrollChildSwipeRefreshLayout.setSwipeRefreshLayoutOnRefreshListener(
        viewModel: HomeViewModel) {
        setOnRefreshListener { viewModel.refresh() }
    }

}
