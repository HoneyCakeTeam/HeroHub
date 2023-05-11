package com.example.herohub.ui.character

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.databinding.FragmentCharactersBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.utills.EventObserve

class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_characters
    override val viewModel: CharactersViewModel by viewModels()

    override fun setup() {
        initiateAdapter()
        observeCharacterClick()
    }

    private fun observeCharacterClick() {
        viewModel.characterClick.observe(this, EventObserve { characterId ->
            navigateToCharacterDetails(characterId)
        })
    }

    private fun navigateToCharacterDetails(characterId: Int) {
        val action = CharactersFragmentDirections
            .actionCharactersFragmentToCharactersDetailsFragment(characterId)
        findNavController().navigate(action)
    }

    private fun initiateAdapter() {
        val adapter = CharactersAdapter(viewModel)
        binding.recyclerCharacters.adapter = adapter
    }

}