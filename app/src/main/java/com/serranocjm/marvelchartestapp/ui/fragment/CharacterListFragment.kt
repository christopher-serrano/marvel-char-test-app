package com.serranocjm.marvelchartestapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.databinding.FragmentCharacterListBinding
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapter
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.HeroItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.type.factory.HeroTypeFactoryImpl
import com.serranocjm.marvelchartestapp.ui.viewmodel.CharacterViewModel
import com.serranocjm.marvelchartestapp.utils.general.toastShort
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterListFragment : BaseFragment(), KoinComponent {

    // Inject viewmodel
    private val characterViewModel: CharacterViewModel by inject()

    // Set up fragment binding
    private var _binding: FragmentCharacterListBinding? = null

    // This property is valid only betweet onCreateView and onDestroy
    private val binding get() = _binding!!

    // Variables
    private var offset: Int? = null
    private var characterList: List<Hero>? = null
    private var characterItemModelList: List<ItemModel>? = null
    private var heroListAdapter: DynamicAdapter? = null

    // Fragment functions
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Observe viewmodel
        observeViewModel()
        // Inflate the layout for this fragment
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setOffset()
        loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Viewmodel functions
    private fun observeViewModel() = characterViewModel.run {
        loadingState.observe(viewLifecycleOwner) {
            Log.d("TAGTAG", "loading!")
        }
        offsetValue.observe(viewLifecycleOwner) {
            offset = it
        }
        heroList.observe(viewLifecycleOwner) {
            characterList = it
            characterList?.let {
                setUpDynamicAdapter()
            }
        }
    }

    private fun loadData() {
        offset?.let {
            characterViewModel.getHeroList(it)
        }
    }

    private fun setOffset() {
        if (offset == null) {
            characterViewModel.setRequestOffset(0)
        }
    }

    // Dynamic Adapter

    override fun setUpDynamicAdapter() {
        characterItemModelList = getHeroListForAdapter(characterList)
        heroListAdapter = getHeroListAdapter(characterItemModelList?.toMutableList(), onItemClick)
        binding.rvHeroList.adapter = heroListAdapter
        binding.rvHeroList.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun getHeroListAdapter(
        list: MutableList<ItemModel>?,
        onItemClick: (ItemModel, String) -> Unit
    ): DynamicAdapter = DynamicAdapter(
        typeFactory = HeroTypeFactoryImpl(),
        items = list?.toMutableList() ?: mutableListOf(),
        onClick = onItemClick,
        imageLoader = imageLoader
    )

    private fun getHeroListForAdapter(list: List<Hero>?): List<ItemModel> {
        val result = mutableListOf<ItemModel>()
        list?.forEach { hero ->
            result.add(HeroItemModel(hero))
        }
        return result
    }

    private var onItemClick: (ItemModel, String) -> Unit = { item, action ->
        val hero: HeroItemModel = item as HeroItemModel

        when (action) {
            "goto_hero_detail" -> requireActivity().toastShort("${hero.model.id} - ${hero.model.name}")
            else -> {
                Log.d("TAGTAG", "Other action.")
            }
        }
    }
}
