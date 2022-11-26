package com.serranocjm.marvelchartestapp.ui.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.HeroItemModel

object HeroDiffCallback {
    fun heroModelCallback() = object : DiffUtil.ItemCallback<HeroItemModel>() {
        override fun areItemsTheSame(oldItem: HeroItemModel, newItem: HeroItemModel): Boolean {
            return oldItem.model.id == newItem.model.id
        }

        override fun areContentsTheSame(oldItem: HeroItemModel, newItem: HeroItemModel): Boolean {
            return oldItem.model == newItem.model
        }
    }
}
