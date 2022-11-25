package com.serranocjm.marvelchartestapp.ui.adapter.type.factory

import com.serranocjm.marvelchartestapp.data.model.character.CommonResourceDetail
import com.serranocjm.marvelchartestapp.data.model.character.Url
import com.serranocjm.marvelchartestapp.ui.adapter.base.BaseTypeFactory

interface HeroDetailTypeFactory : BaseTypeFactory {
    fun typeUrl(type: Url): Int
    fun typeCommonResource(type: CommonResourceDetail): Int
}
