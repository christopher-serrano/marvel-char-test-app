package com.serranocjm.marvelchartestapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.databinding.FragmentCharacterListBinding
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapter
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.HeroItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.type.factory.HeroTypeFactoryImpl
import com.serranocjm.marvelchartestapp.ui.viewmodel.CharacterViewModel
import com.serranocjm.marvelchartestapp.utils.delegates.PreferenceDelegate.Companion.requestOffsetPref
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
    private var characterList: List<Hero>? = null
    private var characterItemModelList: List<ItemModel>? = null
    private var heroListAdapter: DynamicAdapter? = null
    private lateinit var navController: NavController

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
        initValues()
        loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Base frament functions

    override fun initValues() {
        navController = findNavController()
    }

    override fun observeViewModel() = characterViewModel.run {
        loadingState.observe(viewLifecycleOwner) {
            Log.d("TAGTAG", "Loading...")
        }
        heroList.observe(viewLifecycleOwner) {
            characterList = it
            characterList?.apply {
                setUpDynamicAdapter()
            }
        }
    }

    override fun loadData() {
        characterViewModel.getHeroList(requestOffsetPref)
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
