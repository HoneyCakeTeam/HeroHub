package com.example.herohub.ui.characterdetails


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.herohub.R
import com.example.herohub.databinding.FragmentCharactersDetailsBinding
import com.example.herohub.ui.base.BaseFragment


class CharacterDetailsFragment : BaseFragment<FragmentCharactersDetailsBinding>() {
    private val args: CharacterDetailsFragmentArgs by navArgs()

    override val TAG: String = this::class.simpleName.toString()
    override val layoutIdFragment: Int = R.layout.fragment_characters_details
    override val viewModel: CharacterDetailsViewModel by viewModels()

    private lateinit var comicsAdapter: ComicsAdapter

    override fun setup() {
        viewModel.getComicsOfCharacter(args.characterId)
        viewModel.getDetailsOfCharacter(args.characterId)
        viewModel.getSeriesOfCharacter(args.characterId)
        //   handleAdapter()
    }

    private fun handleAdapter() {
        comicsAdapter = ComicsAdapter(viewModel)
        binding.recyclerViewComics.adapter = comicsAdapter
    }


}