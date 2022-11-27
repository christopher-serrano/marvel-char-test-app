package com.serranocjm.marvelchartestapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.serranocjm.marvelchartestapp.databinding.FragmentCharacterListBinding
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.callback.HeroDiffCallback
import com.serranocjm.marvelchartestapp.ui.adapter.custom.paging.HeroListPagingAdapter
import com.serranocjm.marvelchartestapp.ui.adapter.custom.paging.RetryStateLoader
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.HeroItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.type.factory.HeroTypeFactoryImpl
import com.serranocjm.marvelchartestapp.ui.viewmodel.CharacterViewModel
import com.serranocjm.marvelchartestapp.utils.general.toastLong
import com.serranocjm.marvelchartestapp.utils.general.toastShort
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterListFragment : BaseFragment(), KoinComponent {

    // Inject viewmodel
    private val characterViewModel: CharacterViewModel by inject()

    // Set up fragment binding
    private var _binding: FragmentCharacterListBinding? = null

    // This property is valid only between onCreateView and onDestroy
    private val binding get() = _binding!!

    // Variables
    private lateinit var navController: NavController

    // Variables by injection
    private lateinit var heroPagingAdapter: HeroListPagingAdapter

    // Fragment functions
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Observe viewmodel
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize adapter
        heroPagingAdapter = getHeroPagingAdapter()

        // Load data and submit to adapter
        loadData()

        // Set up paging adapter loading state for progress bar
        setUpHeroPagingAdapterProgress()

        // Set up paging adapter
        setUpHeroPagingAdapter()

        // Set up paging adapter retry logic
        setUpHeroPagingAdapterRetry()
    }

    override fun onResume() {
        super.onResume()
        initValues()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Base frament functions

    override fun initValues() {
        navController = findNavController()
    }

    override fun loadData() {
        // characterViewModel.getHeroList(requestOffsetPref)
        lifecycleScope.launchWhenCreated {
            characterViewModel.heroListFlow.collect {
                heroPagingAdapter.submitData(it)
            }
        }
    }

    // Dynamic Adapter

    private fun setUpHeroPagingAdapter() {
        binding.rvHeroList.apply {
            adapter = heroPagingAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun setUpHeroPagingAdapterProgress() {
        lifecycleScope.launchWhenCreated {
            heroPagingAdapter.loadStateFlow.collect {
                val state = it.refresh
                binding.clLoading.isVisible = state is LoadState.Loading
                if (state is LoadState.Error) {
                    requireActivity().toastLong("${state.error.message}")
                }
            }
        }
    }

    private fun setUpHeroPagingAdapterRetry() {
        binding.rvHeroList.adapter = heroPagingAdapter.withLoadStateFooter(
            RetryStateLoader { heroPagingAdapter.retry() }
        )
    }

    private fun getHeroPagingAdapter() = HeroListPagingAdapter(
        typeFactory = HeroTypeFactoryImpl(),
        imageLoader = imageLoader,
        onClick = onItemClick,
        diffCallback = HeroDiffCallback.heroModelCallback()
    )

    private var onItemClick: (ItemModel, String) -> Unit = { item, action ->
        val hero: HeroItemModel = item as HeroItemModel

        when (action) {
            "goto_hero_detail" -> {
                requireActivity().toastShort("${hero.model.id} - ${hero.model.name}")
                hero.model.id?.let {
                    navController.navigate(
                        CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                            it
                        )
                    )
                }
            }
            else -> {
                Log.d("TAGTAG", "Other action.")
            }
        }
    }
}
