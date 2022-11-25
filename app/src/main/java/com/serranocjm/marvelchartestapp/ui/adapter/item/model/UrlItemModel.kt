package com.serranocjm.marvelchartestapp.ui.adapter.item.model

import com.serranocjm.marvelchartestapp.data.model.character.Url
import com.serranocjm.marvelchartestapp.ui.adapter.base.BaseTypeFactory
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel

class UrlItemModel(val model: Url): ItemModel() {
    override fun type(typeFactory: BaseTypeFactory): Int {
        TODO("Not yet implemented")
    }
}