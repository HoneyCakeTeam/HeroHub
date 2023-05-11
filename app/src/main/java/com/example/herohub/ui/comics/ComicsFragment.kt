package com.example.herohub.ui.comics

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.databinding.FragmentComicsBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.utills.EventObserve

class ComicsFragment : BaseFragment<FragmentComicsBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_comics
    override val viewModel: ComicsViewModel by viewModels()

    override fun setup() {
        initiateAdapter()
        observeComicClick()
    }

    private fun initiateAdapter() {
        val adapter = ComicsAdapter(viewModel)
        binding.recyclerComics.adapter = adapter
    }

    private fun observeComicClick() {
        viewModel.comicClick.observe(this, EventObserve { comicId ->
            navigateToComicDetails(comicId)
        })
    }

    private fun navigateToComicDetails(comicId: Int) {
        val action = ComicsFragmentDirections
            .actionComicsFragmentToComicsDetailsFragment(comicId)
        findNavController().navigate(action)
    }

}