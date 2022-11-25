package com.serranocjm.marvelchartestapp.ui.adapter.item.model

import com.serranocjm.marvelchartestapp.data.model.character.CommonResource
import com.serranocjm.marvelchartestapp.ui.adapter.base.BaseTypeFactory
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel

class CommonResourceItemModel(val model: CommonResource) : ItemModel() {
    override fun type(typeFactory: BaseTypeFactory): Int {
        TODO("Not yet implemented")
    }
}
