package com.serranocjm.marvelchartestapp.ui.adapter.base

import android.view.View
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader

interface BaseTypeFactory {
    fun holder(type: Int, view: View, imageLoader: ImageLoader): DynamicAdapterViewHolder<*>
}
