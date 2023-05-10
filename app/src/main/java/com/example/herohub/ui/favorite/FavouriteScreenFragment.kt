package com.example.herohub.ui.favorite

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentFavouriteScreenBinding
import com.example.herohub.ui.base.BaseFragment

class FavouriteScreenFragment : BaseFragment<FragmentFavouriteScreenBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_favourite_screen
    override val viewModel: FavoriteViewModel by viewModels()
    private val adapter: FavoritesAdapter by lazy {
        FavoritesAdapter(viewModel)
    }

    override fun setup() {
        viewModel.retrieveFavorites()
        refreshRecycle()
    }

    private fun refreshRecycle() {
        viewModel.isListChanged.observe(viewLifecycleOwner) { it ->
            if (it) {
                viewModel.favorites.value?.let { adapter.setItems(it) }
                binding.recyclerFavorites.adapter = adapter
                viewModel.resetIsListChanged()
            }
        }
    }
}