package com.example.herohub.ui.seriesdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.data.remote.model.DataResponse
import com.example.herohub.data.domain.model.FavoriteItem
import com.example.herohub.data.domain.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.UiState

class SeriesDetailsViewModel(state: SavedStateHandle) : BaseViewModel() {
    override val TAG: String
        get() = this::class.java.simpleName.toString()
    private val seriesArgs = SeriesDetailsFragmentArgs.fromSavedStateHandle(state)

    private val marvelRepository: MarvelRepository by lazy { MarvelRepository() }

    private val _seriesDetails = MutableLiveData<UiState<DataResponse<Series>?>>()
    val seriesDetails: LiveData<UiState<DataResponse<Series>?>>
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
        disposeSingle(
            marvelRepository.getSeriesDetails(seriesArgs.seriesId),
            ::onSeriesSuccessData,
            ::onError
        )
    }

    private fun onSeriesSuccessData(seriesUiState: UiState<DataResponse<Series>?>) {
        _seriesDetails.value =seriesUiState
        seriesUiState.toData()?.let {
            _series.value = it.results?.get(0)
        }
        _isFavorite.value = marvelRepository.isFavorite(series.value?.id.toString())
    }

    private fun onError(errorMessage: Throwable) {
        _seriesDetails.postValue(UiState.Error(errorMessage.message.toString()))
    }
    fun onFavClicked() {
        val favoriteItem = FavoriteItem(
            _series.value?.id.toString(),
            _series.value?.title.toString(),
            _series.value?.imageUrl.toString(),
            FavoriteItem.Type.SERIES
        )

        if (isFavorite.value == true) {
            marvelRepository.removeFavorite(favoriteItem)
            _isFavorite.postValue(false)
        } else {
            marvelRepository.addToFavorite(favoriteItem)
            _isFavorite.postValue(true)
        }
    }

}