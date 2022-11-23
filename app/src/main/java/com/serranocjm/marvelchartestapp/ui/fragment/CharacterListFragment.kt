package com.serranocjm.marvelchartestapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.databinding.FragmentCharacterListBinding
import com.serranocjm.marvelchartestapp.ui.viewmodel.CharacterViewModel
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
}
