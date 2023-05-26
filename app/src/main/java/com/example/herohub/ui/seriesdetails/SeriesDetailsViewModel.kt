package com.example.herohub.ui.seriesdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(
    private val marvelRepositoryImp: MarvelRepository,
    state: SavedStateHandle
) : BaseViewModel() {
    override val TAG: String
        get() = this::class.java.simpleName.toString()
    private val seriesArgs = SeriesDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _seriesDetails = MutableLiveData<UiState<List<Series>?>>()
    val seriesDetails: LiveData<UiState<List<Series>?>>
        get() = _seriesDetails

    private val _series = MutableLiveData<Series>()
    val series: LiveData<Series>
        get() = _series
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    init {
        getSeriesDetails()
    }

    private fun getSeriesDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepositoryImp.getSeriesDetails(seriesArgs.seriesId).collect {
                withContext(Dispatchers.Main) {
                    onSeriesData(it)
                }
            }
        }
    }

    private fun onSeriesData(seriesUiState: UiState<List<Series>?>) {
        _seriesDetails.value = seriesUiState
        seriesUiState.toData()?.let {
            _series.value = it.get(0)
        }
        _isFavorite.value = marvelRepositoryImp.isFavorite(series.value?.id.toString())
    }

    fun onFavClicked() {
        val favoriteItem = FavoriteItem(
            _series.value?.id.toString(),
            _series.value?.title.toString(),
            _series.value?.imageUrl.toString(),
            FavoriteItem.Type.SERIES
        )

        if (isFavorite.value == true) {
            marvelRepositoryImp.removeFavorite(favoriteItem)
            _isFavorite.postValue(false)
        } else {
            marvelRepositoryImp.addToFavorite(favoriteItem)
            _isFavorite.postValue(true)
        }
    }

}