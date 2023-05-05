package com.example.herohub.ui.category.characters

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentCharactersBinding
import com.example.herohub.ui.base.BaseFragment

class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_characters
    override val viewModel: CharactersViewModel by viewModels()

    override fun setup() {
        initiateAdapter()
    }

    private fun initiateAdapter() {
        val adapter = CharactersAdapter(viewModel)
        binding.recyclerCharacters.adapter = adapter
    }


}