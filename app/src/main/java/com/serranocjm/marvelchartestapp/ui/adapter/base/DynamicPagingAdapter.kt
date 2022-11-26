package com.serranocjm.marvelchartestapp.ui.adapter.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader

class DynamicPagingAdapter(
    private val typeFactory: BaseTypeFactory,
    items: List<ItemModel> = listOf(),
    private val imageLoader: ImageLoader,
    private val onClick: (ItemModel, String) -> Unit = { _, _ -> },
    private val diffCallback: DiffUtil.ItemCallback<Any>
) : PagingDataAdapter<Any, DynamicAdapterViewHolder<ItemModel>>(diffCallback) {

    var mItems: MutableList<ItemModel> = items.toMutableList()
    private var mDiffer: AsyncListDiffer<ItemModel>? = null
    private var mDiffCallback: DiffUtil.ItemCallback<ItemModel>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): DynamicAdapterViewHolder<ItemModel> {
        val view = LayoutInflater.from(parent.context).inflate(p1, parent, false)
        return typeFactory.holder(p1, view, imageLoader) as DynamicAdapterViewHolder<ItemModel>
    }

    fun setCallback(callback: DiffUtil.ItemCallback<ItemModel>) {
        mDiffCallback = callback
        mDiffer = AsyncListDiffer(this, mDiffCallback!!)
    }

    fun submitList(data: List<ItemModel>) {
        mItems = data.toMutableList()
        mDiffer?.submitList(mItems)
    }

    override fun getItemViewType(position: Int): Int {
        return mItems[position].type(typeFactory)
    }

    override fun getItemCount() = mItems.count()

    override fun onBindViewHolder(holder: DynamicAdapterViewHolder<ItemModel>, position: Int) {
        holder.bind(mItems[position], position, onClick)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ItemModel>) {
        mItems.clear()
        mItems.addAll(list)
        notifyDataSetChanged()
    }

    fun insertAtPosition(list: List<ItemModel>, position: Int) {
        mItems.addAll(position, list)
        notifyItemRangeInserted(position, list.size)
        notifyItemChanged(list.size)
    }

    fun removeItems(list: List<ItemModel>) {
        val size = mItems.size
        mItems = list.toMutableList()
        notifyItemRangeRemoved(0, size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(item: ItemModel) {
        mItems.remove(item)
        notifyDataSetChanged()
    }

    fun insertItem(item: ItemModel) {
        val pos = mItems.size
        mItems.add(item)
        notifyItemInserted(pos)
    }

    fun removeLastItem() {
        val item = mItems.last()
        val position = mItems.indexOf(item)
        mItems.removeLast()
        notifyItemRemoved(position)
    }

    fun clear() {
        val size = mItems.size
        mItems.clear()
        notifyItemRangeRemoved(0, size)
    }
}
