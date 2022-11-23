package com.serranocjm.marvelchartestapp.ui.adapter.callback

import com.serranocjm.marvelchartestapp.ui.adapter.base.BaseDiffCallback
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.HeroItemModel

object HeroDiffCallback {
    fun heroModelCallback() = object : BaseDiffCallback() {
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return if (oldItem is HeroItemModel && newItem is HeroItemModel) {
                oldItem.model.id == newItem.model.id
            } else false
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return if (oldItem is HeroItemModel && newItem is HeroItemModel) {
                oldItem.model == newItem.model
            } else false
        }
    }
}
