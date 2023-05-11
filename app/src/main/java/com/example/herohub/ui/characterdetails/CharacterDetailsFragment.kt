package com.example.herohub.ui.characterdetails


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.databinding.FragmentCharactersDetailsBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.comics.ComicsFragmentDirections
import com.example.herohub.utills.EventObserve


class CharacterDetailsFragment : BaseFragment<FragmentCharactersDetailsBinding>() {

    override val TAG: String = this::class.simpleName.toString()
    override val layoutIdFragment: Int = R.layout.fragment_characters_details
    override val viewModel: CharacterDetailsViewModel by viewModels()

    private lateinit var comicsAdapter: ComicsAdapter

    override fun setup() {
        initComicAdapter()
        //collectEvent()
    }

    private fun initComicAdapter() {
        comicsAdapter = ComicsAdapter(viewModel)
        binding.recyclerViewComics.adapter = comicsAdapter
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
        }
        findNavController().navigate(action)
    }


}