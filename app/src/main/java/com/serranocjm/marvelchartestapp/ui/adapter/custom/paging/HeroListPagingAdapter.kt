package com.serranocjm.marvelchartestapp.ui.adapter.custom.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapterViewHolder
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.HeroItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.type.factory.HeroTypeFactoryImpl
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader
import org.koin.core.component.KoinComponent

class HeroListPagingAdapter(
    private val typeFactory: HeroTypeFactoryImpl,
    private val imageLoader: ImageLoader,
    private val onClick: (ItemModel, String) -> Unit = { _, _ -> },
    diffCallback: DiffUtil.ItemCallback<HeroItemModel>
) : PagingDataAdapter<HeroItemModel, DynamicAdapterViewHolder<HeroItemModel>>(diffCallback),
    KoinComponent {

    private var mDiffer: AsyncListDiffer<HeroItemModel>? = null
    private var mDiffCallback: DiffUtil.ItemCallback<HeroItemModel>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): DynamicAdapterViewHolder<HeroItemModel> {
        val view = LayoutInflater.from(parent.context).inflate(p1, parent, false)
        return typeFactory.holder(p1, view, imageLoader) as DynamicAdapterViewHolder<HeroItemModel>
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.type(typeFactory) ?: 0
    }

    override fun onBindViewHolder(holder: DynamicAdapterViewHolder<HeroItemModel>, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item, position, onClick)
            holder.setIsRecyclable(false)
        }
    }
}
