package com.serranocjm.marvelchartestapp.ui.adapter.holder

import android.view.View
import com.serranocjm.marvelchartestapp.databinding.ItemCommonResourceBinding
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapterViewHolder
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.CommonResourceItemModel
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader

class ItemCommonResourceHolder(val view: View, imageLoader: ImageLoader) :
    DynamicAdapterViewHolder<CommonResourceItemModel>(view, imageLoader) {

    private var binding = ItemCommonResourceBinding.bind(view)

    override fun bind(
        item: CommonResourceItemModel,
        position: Int,
        onClick: (ItemModel, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}
