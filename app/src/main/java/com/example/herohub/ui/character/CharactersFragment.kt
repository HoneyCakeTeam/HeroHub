package com.example.herohub.ui.character

import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.herohub.R
import com.example.herohub.databinding.FragmentCharactersBinding
import com.example.herohub.ui.base.BaseFragment

class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_characters
    override val viewModel: CharactersViewModel by viewModels()

    override fun setup() {
        initiateAdapter()
        navigateToCharacterDetails()
    }

    private fun navigateToCharacterDetails() {
        viewModel.characterId.observe(this) { characterId ->
            val action = CharactersFragmentDirections
                .actionCharactersFragmentToCharactersDetailsFragment(characterId)
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun initiateAdapter() {
        val adapter = CharactersAdapter(viewModel)
        binding.recyclerCharacters.adapter = adapter
    }

}