package com.serranocjm.marvelchartestapp.ui.adapter.item.model

import com.serranocjm.marvelchartestapp.data.model.character.CommonResource
import com.serranocjm.marvelchartestapp.ui.adapter.base.BaseTypeFactory
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.type.factory.HeroDetailTypeFactory

class CommonResourceItemModel(val model: CommonResource) : ItemModel() {
    override fun type(typeFactory: BaseTypeFactory): Int {
        return (typeFactory as HeroDetailTypeFactory).typeCommonResource(model)
    }
}
