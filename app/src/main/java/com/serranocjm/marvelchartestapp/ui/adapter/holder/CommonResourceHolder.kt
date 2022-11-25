package com.serranocjm.marvelchartestapp.ui.adapter.holder

import android.view.View
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapterViewHolder
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.CommonResourceItemModel
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader

class CommonResourceHolder(val view: View, imageLoader: ImageLoader) :
    DynamicAdapterViewHolder<CommonResourceItemModel>(view, imageLoader) {
    override fun bind(
        item: CommonResourceItemModel,
        position: Int,
        onClick: (ItemModel, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}
