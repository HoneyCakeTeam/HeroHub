package com.example.herohub.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val marvelRepositoryImp: MarvelRepository,
) : BaseViewModel(), SeriesInteractionListener {
    override val TAG: String = this::class.java.simpleName
    private val _allSeries = MutableLiveData<UiState<List<Series>>>()
    val allSeries: LiveData<UiState<List<Series>>>
        get() = _allSeries

    private val _seriesClick = MutableLiveData<EventHandler<Int>>()
    val seriesClick: LiveData<EventHandler<Int>>
        get() = _seriesClick

    init {
        getAllSeries()
    }

    private fun getAllSeries() {
        _allSeries.postValue(UiState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepositoryImp.getAllSeries().collect {
                withContext(Dispatchers.Main) {
                    onGetSeries(it)
                }
            }
        }
    }

    private fun onGetSeries(state: UiState<List<Series>>) {
        _allSeries.postValue(state)
    }


    override fun onClickSeries(id: Int) {
        _seriesClick.postValue(EventHandler(id))
    }

}