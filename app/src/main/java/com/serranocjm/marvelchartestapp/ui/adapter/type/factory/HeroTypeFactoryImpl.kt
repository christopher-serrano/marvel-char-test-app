package com.serranocjm.marvelchartestapp.ui.adapter.type.factory

import android.view.View
import com.serranocjm.marvelchartestapp.R
import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapterViewHolder
import com.serranocjm.marvelchartestapp.ui.adapter.holder.ItemHeroHolder
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader

class HeroTypeFactoryImpl : HeroTypeFactory {
    override fun holder(
        type: Int,
        view: View,
        imageLoader: ImageLoader
    ): DynamicAdapterViewHolder<*> {
        return when (type) {
            R.layout.item_hero -> ItemHeroHolder(view, imageLoader)
            else -> throw RuntimeException("Illegal view type.")
        }
    }

    override fun typeHero(type: Hero): Int = R.layout.item_hero
}
