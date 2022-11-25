package com.serranocjm.marvelchartestapp.ui.adapter.holder

import android.view.View
import com.serranocjm.marvelchartestapp.data.getValue
import com.serranocjm.marvelchartestapp.databinding.ItemUrlBinding
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapterViewHolder
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.UrlItemModel
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader
import com.serranocjm.marvelchartestapp.utils.general.setOneOffClickListener

class ItemUrlHolder(val view: View, imageLoader: ImageLoader) :
    DynamicAdapterViewHolder<UrlItemModel>(view, imageLoader) {

    private var binding = ItemUrlBinding.bind(view)

    override fun bind(item: UrlItemModel, position: Int, onClick: (ItemModel, String) -> Unit) {
        binding.tvUrlType.text = item.model.type?.getValue()
        binding.tvUrlValue.apply {
            text = item.model.url
            setOneOffClickListener {
                onClick(item, "goto_url")
            }
        }
    }
}
