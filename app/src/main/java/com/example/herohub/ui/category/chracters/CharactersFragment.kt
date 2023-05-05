package com.example.herohub.ui.category.chracters

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.herohub.R
import com.example.herohub.databinding.FragmentCharactersBinding
import com.example.herohub.ui.base.BaseFragment

class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_characters
    override val viewModel: ViewModel by viewModels()

    override fun setup() {

    }

}