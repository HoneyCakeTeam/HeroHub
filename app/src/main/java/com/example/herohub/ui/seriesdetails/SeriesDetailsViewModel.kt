package com.example.herohub.ui.seriesdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.herohub.data.remote.model.DataResponse
import com.example.herohub.data.repository.MarvelRepositoryImpl
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.UiState

class SeriesDetailsViewModel(state: SavedStateHandle) : BaseViewModel() {
    override val TAG: String
        get() = this::class.java.simpleName.toString()
    private val seriesArgs = SeriesDetailsFragmentArgs.fromSavedStateHandle(state)

    private val marvelRepositoryImpl: MarvelRepositoryImpl by lazy { MarvelRepositoryImpl() }

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
            marvelRepositoryImpl.getSeriesDetails(seriesArgs.seriesId),
            ::onSeriesSuccessData,
            ::onError
        )
    }

    private fun onSeriesSuccessData(seriesUiState: UiState<DataResponse<Series>?>) {
        _seriesDetails.value =seriesUiState
        seriesUiState.toData()?.let {
            _series.value = it.results?.get(0)
        }
        _isFavorite.value = marvelRepositoryImpl.isFavorite(series.value?.id.toString())
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
            marvelRepositoryImpl.removeFavorite(favoriteItem)
            _isFavorite.postValue(false)
        } else {
            marvelRepositoryImpl.addToFavorite(favoriteItem)
            _isFavorite.postValue(true)
        }
    }

}