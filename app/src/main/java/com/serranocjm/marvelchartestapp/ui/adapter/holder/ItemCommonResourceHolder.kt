package com.serranocjm.marvelchartestapp.ui.adapter.holder

import android.view.View
import com.serranocjm.marvelchartestapp.databinding.ItemCommonResourceBinding
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapterViewHolder
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.CommonResourceDetailItemModel
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader

class ItemCommonResourceHolder(val view: View, imageLoader: ImageLoader) :
    DynamicAdapterViewHolder<CommonResourceDetailItemModel>(view, imageLoader) {

    private var binding = ItemCommonResourceBinding.bind(view)

    override fun bind(
        item: CommonResourceDetailItemModel,
        position: Int,
        onClick: (ItemModel, String) -> Unit
    ) {
        binding.tvItemValue.text = item.model.name
    }
}
