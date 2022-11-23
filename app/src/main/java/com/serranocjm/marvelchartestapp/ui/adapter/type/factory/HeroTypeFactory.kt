package com.serranocjm.marvelchartestapp.ui.adapter.type.factory

import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.ui.adapter.base.BaseTypeFactory

interface HeroTypeFactory : BaseTypeFactory {
    fun typeHero(type: Hero): Int
}
