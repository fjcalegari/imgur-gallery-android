package com.fjcalegari.imgur.ui.modules.home

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.fjcalegari.imgur.R
import com.fjcalegari.imgur.databinding.ActivityHomeBinding
import com.fjcalegari.imgur.ui.modules.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: HomeViewModel

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProviders.of(this, this.viewModelFactory).get(HomeViewModel::class.java)

        with(binding) {
            lifecycleOwner = this@HomeActivity
            viewmodel = viewModel
        }

        with(binding.rvGalleries) {
            adapter = HomeGalleryAdapter()
            addItemDecoration(ItemOffsetDecoration(this@HomeActivity))
        }

    }

    inner class ItemOffsetDecoration(
        context: Context

    ): RecyclerView.ItemDecoration() {

        private var itemOffset: Int = context.resources.getDimensionPixelSize(R.dimen.spacingMedium)

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.set(itemOffset, itemOffset, itemOffset, itemOffset)
        }

    }

}
