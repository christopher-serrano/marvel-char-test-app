package com.serranocjm.marvelchartestapp.ui.adapter.custom.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.serranocjm.marvelchartestapp.databinding.ItemRetryLayoutBinding
import com.serranocjm.marvelchartestapp.utils.general.setOneOffClickListener

class RetryStateLoader(private val block: () -> Unit) :
    LoadStateAdapter<RetryStateLoader.ViewHolder>() {
    private lateinit var binding: ItemRetryLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        binding = ItemRetryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(block)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.setData(loadState)
    }

    inner class ViewHolder(retry: () -> Unit) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnLoadMoreRetry.setOneOffClickListener(retry)
        }

        fun setData(state: LoadState) {
            binding.apply {
                pbLoadRetry.isVisible = state is LoadState.Loading
                tvLoadMore.isVisible = state is LoadState.Error
                btnLoadMoreRetry.isVisible = state is LoadState.Error
            }
        }
    }
}
