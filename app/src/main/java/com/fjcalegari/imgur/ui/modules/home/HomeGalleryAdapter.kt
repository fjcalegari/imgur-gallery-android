package com.fjcalegari.imgur.ui.modules.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fjcalegari.imgur.R
import com.fjcalegari.imgur.databinding.ItemGalleryImgBinding
import com.fjcalegari.imgur.domain.model.GalleryModel


class HomeGalleryAdapter : ListAdapter<GalleryModel, HomeGalleryAdapter.ItemHolder>(GalleryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemGalleryImgBinding: ItemGalleryImgBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_gallery_img,
            parent,
            false
        )
        return ItemHolder(itemGalleryImgBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemHolder(
        private val itemGalleryImgBinding: ItemGalleryImgBinding
    ) : RecyclerView.ViewHolder(itemGalleryImgBinding.root) {
        fun bind(galleryModel: GalleryModel) {
            itemGalleryImgBinding.gallery = galleryModel
            itemGalleryImgBinding.executePendingBindings()
        }

    }
}

private class GalleryDiffCallback : DiffUtil.ItemCallback<GalleryModel>() {

    override fun areItemsTheSame(oldItem: GalleryModel, newItem: GalleryModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GalleryModel, newItem: GalleryModel): Boolean {
        return oldItem == newItem
    }
}