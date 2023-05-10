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
        val adapter = FavoritesAdapter(viewModel)
        binding.recyclerFavorites.adapter = adapter
        refreshRecycle()
    }

    private fun refreshRecycle() {
        viewModel.isListChanged.observe(viewLifecycleOwner) { it ->
            if (it) {
                val adapter = FavoritesAdapter(viewModel)
                viewModel.favorites.value?.let { adapter.setItems(it) }
                binding.recyclerFavorites.adapter = adapter
                viewModel.resetIsListChanged()
            }
        }
    }
}