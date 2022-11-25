package com.serranocjm.marvelchartestapp.ui.adapter.type.factory

import com.serranocjm.marvelchartestapp.data.model.character.CommonResource
import com.serranocjm.marvelchartestapp.data.model.character.Url
import com.serranocjm.marvelchartestapp.ui.adapter.base.BaseTypeFactory

interface HeroDetailFactory : BaseTypeFactory {
    fun typeUrl(type: Url): Int
    fun typeCommonResource(type: CommonResource): Int
}
