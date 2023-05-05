package com.example.herohub.ui.category.comics

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentComicsBinding
import com.example.herohub.ui.base.BaseFragment


class ComicsFragment : BaseFragment<FragmentComicsBinding>() {
    override val TAG: String = this::class.java.simpleName.toString()
    override val layoutIdFragment: Int = R.id.comicsFragment
    override val viewModel: ComicsViewModel by viewModels()

    override fun setup() {
        initiateAdapter()
    }

    private fun initiateAdapter() {
        val adapter = ComicsAdapter(viewModel)
        binding.recyclerComics.adapter = adapter
    }

}