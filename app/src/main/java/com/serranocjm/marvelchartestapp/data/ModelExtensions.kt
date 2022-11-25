package com.serranocjm.marvelchartestapp.data // ktlint-disable filename

import com.serranocjm.marvelchartestapp.utils.general.Constants

fun String.getValue(): String {
    return when (this) {
        Constants.STORY_COVER -> Constants.STORY_COVER_VALUE
        Constants.STORY_INTERIOR_VALUE -> Constants.STORY_INTERIOR_VALUE
        Constants.URL_DETAIL -> Constants.URL_DETAIL_VALUE
        Constants.URL_WIKI -> Constants.URL_WIKI_VALUE
        Constants.URL_COMIC_LINK -> Constants.URL_COMIC_LINK_VALUE
        else -> "N/A"
    }
}
