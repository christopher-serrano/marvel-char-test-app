package com.serranocjm.marvelchartestapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.databinding.FragmentCharacterDetailBinding
import com.serranocjm.marvelchartestapp.ui.viewmodel.CharacterViewModel
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
            Log.d("TAGTAG", "Loading...")
        }
        heroDetail.observe(viewLifecycleOwner) {
            characterDetail = it
        }
    }
}
