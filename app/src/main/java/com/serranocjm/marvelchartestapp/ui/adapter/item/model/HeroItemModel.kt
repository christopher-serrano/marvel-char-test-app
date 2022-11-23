package com.serranocjm.marvelchartestapp.ui.adapter.item.model

import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.ui.adapter.base.BaseTypeFactory
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.type.factory.HeroTypeFactory

class HeroItemModel(val model: Hero) : ItemModel() {
    override fun type(typeFactory: BaseTypeFactory): Int {
        return (typeFactory as HeroTypeFactory).typeHero(model)
    }
}
