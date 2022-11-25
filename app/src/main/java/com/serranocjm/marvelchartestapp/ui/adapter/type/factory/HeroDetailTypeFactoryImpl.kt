package com.serranocjm.marvelchartestapp.ui.adapter.type.factory

import android.view.View
import com.serranocjm.marvelchartestapp.R
import com.serranocjm.marvelchartestapp.data.model.character.CommonResource
import com.serranocjm.marvelchartestapp.data.model.character.Url
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapterViewHolder
import com.serranocjm.marvelchartestapp.ui.adapter.holder.CommonResourceHolder
import com.serranocjm.marvelchartestapp.ui.adapter.holder.UrlHolder
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader

class HeroDetailTypeFactoryImpl : HeroDetailTypeFactory {
    override fun holder(
        type: Int,
        view: View,
        imageLoader: ImageLoader
    ): DynamicAdapterViewHolder<*> {
        return when (type) {
            R.layout.item_url -> UrlHolder(view, imageLoader)
            R.layout.item_common_resource -> CommonResourceHolder(view, imageLoader)
            else -> throw RuntimeException("Illegal view type.")
        }
    }

    override fun typeUrl(type: Url): Int = R.layout.item_url

    override fun typeCommonResource(type: CommonResource): Int = R.layout.item_common_resource
}
