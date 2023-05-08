package com.example.herohub.ui.favorite

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentFavouriteScreenBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.utills.shared.SharedPreferencesUtils

class FavouriteScreenFragment : BaseFragment<FragmentFavouriteScreenBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_favourite_screen
    override val viewModel: FavoriteViewModel by viewModels()

    override fun setup() {
        viewModel.initShared(requireContext())
        val adapter = FavoritesAdapter(viewModel)
        binding.recyclerFavorites.adapter = adapter
        viewModel.addFavorite()
        viewModel.retrieveFavorites()
    }
}