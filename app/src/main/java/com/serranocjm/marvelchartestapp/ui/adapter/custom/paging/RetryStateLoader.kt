package com.serranocjm.marvelchartestapp.ui.adapter.custom.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.serranocjm.marvelchartestapp.databinding.LoadRetryLayoutBinding
import com.serranocjm.marvelchartestapp.utils.general.toggleVisibility

class RetryStateLoader(private val block: () -> Unit) :
    LoadStateAdapter<RetryStateLoader.ViewHolder>() {
    private lateinit var binding: LoadRetryLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        binding = LoadRetryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(block)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.setData(loadState)
    }

    inner class ViewHolder(retry: () -> Unit) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnLoadMoreRetry.setOnClickListener { retry() }
        }

        fun setData(state: LoadState) {
            binding.apply {
                pbLoadRetry.toggleVisibility(state is LoadState.Loading)
                tvLoadMore.toggleVisibility(state is LoadState.Error)
                btnLoadMoreRetry.toggleVisibility(state is LoadState.Error)
            }
        }
    }
}
