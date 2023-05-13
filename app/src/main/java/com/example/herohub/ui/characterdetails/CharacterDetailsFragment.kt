package com.example.herohub.ui.characterdetails


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.databinding.FragmentCharactersDetailsBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.characterdetails.adapter.CharacterDetailsAdapter
import com.example.herohub.ui.utils.EventObserve


class CharacterDetailsFragment : BaseFragment<FragmentCharactersDetailsBinding>() {

    override val TAG: String = this::class.simpleName.toString()
    override val layoutIdFragment: Int = R.layout.fragment_characters_details
    override val viewModel: CharacterDetailsViewModel by viewModels()
    private val characterDetailsAdapter by lazy {
        CharacterDetailsAdapter(mutableListOf(), viewModel, viewModel)
    }

    override fun setup() {
        initAdapter()
        collectEvent()
    }

    private fun initAdapter() {
        binding.recyclerViewComics.adapter = characterDetailsAdapter
        binding.recyclerViewComics.adapter
    }

    private fun collectEvent() {
        viewModel.characterDetailsUiEvent.observe(this, EventObserve {
            if (it != null) {
                onEvent(it)
            }
        })
    }

    private fun onEvent(event: CharacterDetailsUiEvent) {
        val action = when (event) {
            is CharacterDetailsUiEvent.ClickCharacterComic -> {
                CharacterDetailsFragmentDirections
                    .actionCharactersDetailsFragmentToComicsDetailsFragment(event.id)
            }

            is CharacterDetailsUiEvent.ClickCharacterEvents -> {
                CharacterDetailsFragmentDirections
                    .actionCharactersDetailsFragmentToEventsDetailsFragment(event.id)

            }

            is CharacterDetailsUiEvent.ClickCharacterSeries -> {
                CharacterDetailsFragmentDirections
                    .actionCharactersDetailsFragmentToSeriesDetailsFragment(event.id)

            }
        }
        findNavController().navigate(action)
    }


}