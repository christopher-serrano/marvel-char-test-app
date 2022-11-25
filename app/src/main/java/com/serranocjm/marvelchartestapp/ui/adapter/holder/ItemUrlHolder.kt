package com.serranocjm.marvelchartestapp.ui.adapter.holder

import android.view.View
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapterViewHolder
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.UrlItemModel
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader

class ItemUrlHolder(val view: View, imageLoader: ImageLoader) :
    DynamicAdapterViewHolder<UrlItemModel>(view, imageLoader) {
    override fun bind(item: UrlItemModel, position: Int, onClick: (ItemModel, String) -> Unit) {
        TODO("Not yet implemented")
    }
}
