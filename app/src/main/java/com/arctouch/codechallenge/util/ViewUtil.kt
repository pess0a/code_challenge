package com.arctouch.codechallenge.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.arctouch.codechallenge.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ViewUtil {

    companion object {

        fun showPoster(view : View, pathImage : String?, imageView: ImageView) {
            Glide.with(view)
                    .load(pathImage.let { MovieImageUrlBuilder.buildPosterUrl(it) })
                    .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                    .into(imageView)
        }

        fun showBackdrop(view : View, pathImage : String?, imageView: ImageView) {
            Glide.with(view)
                    .load(pathImage.let { MovieImageUrlBuilder.buildBackdropUrl(it) })
                    .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                    .into(imageView)
        }

    }
}