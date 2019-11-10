package com.fjcalegari.imgur.ui.common.binding

import android.widget.ImageView
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.fjcalegari.imgur.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:imageLoadUrl")
    fun imageLoadUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url.plus("m.jpg"))
            .apply(RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .placeholder(R.drawable.ic_logo_cat)
            .error(R.drawable.ic_logo_cat)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("app:message")
    fun message(appCompatTextView: AppCompatTextView, @StringRes text: Int?) {
        text?.let {
            appCompatTextView.text = appCompatTextView.context.getString(text)
        }
    }

}
