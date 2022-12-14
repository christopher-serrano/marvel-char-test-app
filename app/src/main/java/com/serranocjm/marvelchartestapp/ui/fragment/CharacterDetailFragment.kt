package com.serranocjm.marvelchartestapp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.serranocjm.marvelchartestapp.R
import com.serranocjm.marvelchartestapp.data.model.character.CommonResourceDetail
import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.data.model.character.Url
import com.serranocjm.marvelchartestapp.databinding.FragmentCharacterDetailBinding
import com.serranocjm.marvelchartestapp.ui.adapter.base.DynamicAdapter
import com.serranocjm.marvelchartestapp.ui.adapter.base.ItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.CommonResourceDetailItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.UrlItemModel
import com.serranocjm.marvelchartestapp.ui.adapter.type.factory.HeroDetailTypeFactoryImpl
import com.serranocjm.marvelchartestapp.ui.viewmodel.CharacterViewModel
import com.serranocjm.marvelchartestapp.utils.general.makeVisible
import com.serranocjm.marvelchartestapp.utils.general.toastLong
import org.koin.core.component.inject

class CharacterDetailFragment : BaseFragment() {

    // Inject viewmodel
    private val characterViewModel: CharacterViewModel by inject()

    // Set up fragment binding
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    // Fragment navigation arguments
    private val args: CharacterDetailFragmentArgs by navArgs()

    // Other variables
    private var characterId: Int = 0
    private var characterDetail: Hero? = null

    // Adapters
    private var urlListAdapter: DynamicAdapter? = null
    private var comicsListAdapter: DynamicAdapter? = null
    private var storiesListAdapter: DynamicAdapter? = null
    private var eventsListAdapter: DynamicAdapter? = null
    private var seriesListAdapter: DynamicAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observeViewModel()
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
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

    // Load and observe data

    override fun initValues() {
        characterId = args.characterId
    }

    override fun loadData() {
        super.loadData()
        characterViewModel.getHeroDetail(characterId)
    }

    override fun observeViewModel() = characterViewModel.run {
        onError.observe(viewLifecycleOwner) {
            requireActivity().toastLong(it)
        }
        loadingState.observe(viewLifecycleOwner) {
            binding.clLoading.isVisible = it
        }
        heroDetail.observe(viewLifecycleOwner) {
            characterDetail = it
            showData()
        }
    }

    // Show data

    private fun showData() {
        characterDetail?.let { hero ->
            binding.tvHeroName.text = hero.name
            hero.description?.let {
                binding.tvDescription.apply {
                    text = it
                    makeVisible()
                }
            }
            hero.thumbnail?.let { image ->
                imageLoader.loadWithUrl(
                    "${image.path}.${image.extension}",
                    binding.ivHeroThumbnail,
                    R.drawable.ic_hero_placeholder
                )
            }
        }
        setUpDynamicAdapter()
    }

    // TODO: finish the layout! add the recyclerviews!!!
    override fun setUpDynamicAdapter() {
        characterDetail?.let { hero ->
            hero.urls?.let {
                urlListAdapter =
                    getDynamicAdapter(getUrlListForAdapter(it).toMutableList(), onItemClick)
                binding.rvUrlList.apply {
                    adapter = urlListAdapter
                    layoutManager = LinearLayoutManager(requireActivity())
                }
                binding.clUrlListContainer.makeVisible()
            }
            hero.comics?.let {
                comicsListAdapter = getDynamicAdapter(
                    getCommonResourceListForAdapter(it.items).toMutableList()
                ) { _, _ -> }
                binding.rvComicList.apply {
                    adapter = comicsListAdapter
                    layoutManager = LinearLayoutManager(requireActivity())
                }
                binding.clComicListcontainer.makeVisible()
            }
            hero.stories?.let {
                storiesListAdapter = getDynamicAdapter(
                    getCommonResourceListForAdapter(it.items).toMutableList()
                ) { _, _ -> }
                binding.rvStoryList.apply {
                    adapter = storiesListAdapter
                    layoutManager = LinearLayoutManager(requireActivity())
                }
                binding.clStoryListcontainer.makeVisible()
            }
            hero.events?.let {
                eventsListAdapter = getDynamicAdapter(
                    getCommonResourceListForAdapter(it.items).toMutableList()

                ) { _, _ -> }
                binding.rvEventList.apply {
                    adapter = eventsListAdapter
                    layoutManager = LinearLayoutManager(requireActivity())
                }
                binding.clEventListcontainer.makeVisible()
            }
            hero.series?.let {
                seriesListAdapter = getDynamicAdapter(
                    getCommonResourceListForAdapter(it.items).toMutableList()
                ) { _, _ -> }
                binding.rvSeriesList.apply {
                    adapter = seriesListAdapter
                    layoutManager = LinearLayoutManager(requireActivity())
                }
                binding.clSeriesListcontainer.makeVisible()
            }
        }
    }

    //
    private fun getDynamicAdapter(
        list: MutableList<ItemModel>?,
        onItemClick: (ItemModel, String) -> Unit
    ): DynamicAdapter =
        DynamicAdapter(
            typeFactory = HeroDetailTypeFactoryImpl(),
            items = list?.toMutableList() ?: mutableListOf(),
            onClick = onItemClick,
            imageLoader = imageLoader
        )

    //
    private fun getCommonResourceListForAdapter(list: List<CommonResourceDetail>?): List<ItemModel> {
        val result = mutableListOf<ItemModel>()
        list?.forEach { item ->
            result.add(CommonResourceDetailItemModel(item))
        }
        return result
    }

    private fun getUrlListForAdapter(list: List<Url>?): List<ItemModel> {
        val result = mutableListOf<ItemModel>()
        list?.forEach { item ->
            result.add(UrlItemModel(item))
        }
        return result
    }

    //
    private val onItemClick: (ItemModel, String) -> Unit = { item, action ->
        val model = item as UrlItemModel

        when (action) {
            "goto_url" -> {
                val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(model.model.url))
                requireActivity().startActivity(intent)
            }
            else -> {
                Log.d("TAGTAG", "Empty action.")
            }
        }
    }
}
