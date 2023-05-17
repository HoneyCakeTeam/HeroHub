package com.example.herohub.ui.favorite

import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.data.remote.model.FavoriteItem
import com.example.herohub.databinding.FragmentFavouriteScreenBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.utils.EventObserve

class FavouriteScreenFragment : BaseFragment<FragmentFavouriteScreenBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_favourite_screen
    override val viewModel: FavoriteViewModel by viewModels()
    private lateinit var action: NavDirections
    private val adapter: FavoritesAdapter by lazy {
        FavoritesAdapter(viewModel)
    }

    override fun setup() {
        viewModel.retrieveFavorites()
        binding.recyclerFavorites.adapter = adapter
        observeCharacterClick()
    }

    private fun observeCharacterClick() {
        viewModel.favoriteClick.observe(this, EventObserve { favoriteItem ->
            navigateToCharacterDetails(favoriteItem)
        })
    }

    private fun navigateToCharacterDetails(favoriteItem: FavoriteItem) {
        action = when (favoriteItem.type) {
            FavoriteItem.Type.CHARACTER -> {
                FavouriteScreenFragmentDirections
                    .actionFavouriteFragmentToCharactersDetailsFragment(favoriteItem.id.toInt())
            }

            FavoriteItem.Type.EVENT -> {
                FavouriteScreenFragmentDirections
                    .actionFavouriteFragmentToEventsDetailsFragment(favoriteItem.id.toInt())
            }

            FavoriteItem.Type.COMIC -> {
                FavouriteScreenFragmentDirections
                    .actionFavouriteFragmentToComicsDetailsFragment(favoriteItem.id.toInt())
            }

            FavoriteItem.Type.SERIES -> {
                FavouriteScreenFragmentDirections
                    .actionFavouriteFragmentToSeriesDetailsFragment(favoriteItem.id.toInt())
            }
        }
        findNavController().navigate(action)
    }
}