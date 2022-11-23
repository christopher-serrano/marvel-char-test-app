package com.serranocjm.marvelchartestapp.ui.adapter.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader

abstract class DynamicAdapterViewHolder<in T>(view: View, val imageLoader: ImageLoader) :
    RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T, position: Int, onClick: (ItemModel, String) -> Unit)
}
