package com.example.rssnews.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object DataBindingAdapters {

    @JvmStatic
    @BindingAdapter("app:image_url")
    fun bindImageByURL(view: ImageView, image_url: String?){

        image_url?.let {
            Picasso.get()
                .load(image_url)
                .fit()
                .error(com.example.rssnews.R.drawable.ic_not_available)
                .placeholder(com.example.rssnews.R.drawable.ic_not_available)
                .into(view)
        }

    }
}