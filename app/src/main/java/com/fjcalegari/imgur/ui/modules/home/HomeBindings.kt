package com.fjcalegari.imgur.ui.modules.home

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fjcalegari.imgur.ui.view.ScrollChildSwipeRefreshLayout

object HomeBindings {

    @BindingAdapter("showWhenLoading")
    @JvmStatic
    fun <T>showWhenLoading(view: SwipeRefreshLayout, viewState: HomeViewModel.ViewState) {
        view.isRefreshing = viewState.isLoading
    }

    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, viewState: HomeViewModel.ViewState) {
        with(recyclerView) {
            (this.adapter as HomeGalleryAdapter).submitList(viewState.items)
            if (viewState.items.isNotEmpty()) {
                this.visibility = View.VISIBLE
            } else {
                this.visibility = View.GONE
            }
        }
    }

    /**
     * Reloads the data when the pull-to-refresh is triggered.
     *
     * Creates the `android:onRefresh` for a [SwipeRefreshLayout].
     */
    @BindingAdapter("android:onRefresh")
    @JvmStatic
    fun ScrollChildSwipeRefreshLayout.setSwipeRefreshLayoutOnRefreshListener(
        viewModel: HomeViewModel
    ) {
        setOnRefreshListener { viewModel.refresh() }
    }

}
