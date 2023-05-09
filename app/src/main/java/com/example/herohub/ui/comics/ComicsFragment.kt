package com.example.herohub.ui.comics

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentComicsBinding
import com.example.herohub.ui.base.BaseFragment

class ComicsFragment : BaseFragment<FragmentComicsBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_comics
    override val viewModel: ComicsViewModel by viewModels()

    override fun setup() {
        initiateAdapter()
//        navigateToComicDetails()
    }

    private fun initiateAdapter() {
        val adapter = ComicsAdapter(viewModel)
        binding.recyclerComics.adapter = adapter
    }

//    private fun navigateToComicDetails() {
//        viewModel.comicId.observe(this) { comicId ->
//            val action = CategoriesFragmentDirections
//                .actionCategoriesFragmentToComicsDetailsFragment(comicId)
//            Navigation.findNavController(binding.root).navigate(action)
//        }
//    }

}