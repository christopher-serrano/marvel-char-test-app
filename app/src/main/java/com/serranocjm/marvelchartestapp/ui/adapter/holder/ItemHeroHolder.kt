package com.serranocjm.marvelchartestapp.ui.adapter.holder

import android.view.View
import com.serranocjm.marvelchartestapp.R
import com.serranocjm.marvelchartestapp.databinding.ItemHeroBinding
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapterViewHolder
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.HeroItemModel
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader
import com.serranocjm.marvelchartestapp.utils.general.setOneOffClickListener

class ItemHeroHolder(val view: View, imageLoader: ImageLoader) :
    DynamicAdapterViewHolder<HeroItemModel>(view, imageLoader) {

    private var binding = ItemHeroBinding.bind(itemView)

    override fun bind(item: HeroItemModel, position: Int, onClick: (ItemModel, String) -> Unit) {
        binding.tvHeroName.text = item.model.name

        item.model.thumbnail?.let { image ->
            imageLoader.loadWithUrl(
                "${image.path}.${image.extension}",
                binding.ivHeroThumbnail,
                R.drawable.ic_hero_placeholder
            )
        }

        binding.ivSeeHeroDetail.setOneOffClickListener {
            onClick.invoke(item, "goto_hero_detail")
        }
    }
}
