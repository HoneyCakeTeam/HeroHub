package com.example.herohub.ui.characterdetails


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.databinding.FragmentCharactersDetailsBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.characterdetails.adapter.CharacterDetailsAdapter
import com.example.herohub.ui.comics.ComicsFragmentDirections
import com.example.herohub.ui.events.EventsFragmentDirections
import com.example.herohub.ui.search.SearchFragmentDirections
import com.example.herohub.utills.EventObserve


class CharacterDetailsFragment : BaseFragment<FragmentCharactersDetailsBinding>() {

    override val TAG: String = this::class.simpleName.toString()
    override val layoutIdFragment: Int = R.layout.fragment_characters_details
    override val viewModel: CharacterDetailsViewModel by viewModels()
    private lateinit var characterDetailsAdapter: CharacterDetailsAdapter
    override fun setup() {
        initAdapter()
        collectEvent()
    }

    private fun initAdapter() {
        characterDetailsAdapter = CharacterDetailsAdapter(mutableListOf(), viewModel)
        binding.recyclerViewComics.adapter = characterDetailsAdapter
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
                ComicsFragmentDirections.actionComicsFragmentToComicsDetailsFragment(event.id)
            }

            is CharacterDetailsUiEvent.ClickCharacterEvents -> {
                EventsFragmentDirections.actionEventsFragmentToEventsDetailsFragment(event.id)

            }

            is CharacterDetailsUiEvent.ClickCharacterSeries -> {
                SearchFragmentDirections.actionSearchFragmentToCharactersDetailsFragment(event.id)

            }
        }
        findNavController().navigate(action)
    }


}