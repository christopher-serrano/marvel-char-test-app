package com.serranocjm.marvelchartestapp.ui.fragment

import androidx.fragment.app.Fragment
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseFragment : Fragment(), KoinComponent {

    protected val imageLoader: ImageLoader by inject()

    open fun initValues() {}
    open fun observeViewModel() {}
    open fun loadData() {}
    open fun setUpDynamicAdapter() {}
}
