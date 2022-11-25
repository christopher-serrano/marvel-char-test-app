package com.serranocjm.marvelchartestapp.ui.adapter.type.factory

import android.view.View
import com.serranocjm.marvelchartestapp.R
import com.serranocjm.marvelchartestapp.data.model.character.CommonResource
import com.serranocjm.marvelchartestapp.data.model.character.Url
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapterViewHolder
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader

class HeroDetailFactoryImpl : HeroDetailFactory {
    override fun holder(
        type: Int,
        view: View,
        imageLoader: ImageLoader
    ): DynamicAdapterViewHolder<*> {
        TODO("Not yet implemented")
    }

    override fun typeUrl(type: Url): Int = R.layout.item_url

    override fun typeCommonResource(type: CommonResource): Int = R.layout.item_common_resource
}