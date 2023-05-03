package com.example.herohub.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import com.example.herohub.model.Creator
import com.example.herohub.model.Event
import com.example.herohub.model.Series
import com.example.herohub.model.Story
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoryViewModel : BaseViewModel() {
    override val TAG: String = this::class.java.simpleName

    private val repository = Repository()

    private val _characters = MutableLiveData<State<Character>>()
    val characters: LiveData<State<Character>> = _characters

    private val _stories = MutableLiveData<State<Story>>()
    val stories: LiveData<State<Story>> = _stories

    private val _events = MutableLiveData<State<Event>>()
    val events: LiveData<State<Event>> = _events

    private val _comics = MutableLiveData<State<Comic>>()
    val comic: LiveData<State<Comic>> = _comics

    private val _series = MutableLiveData<State<Series>>()
    val series: LiveData<State<Series>> = _series

    private val _creators = MutableLiveData<State<Creator>>()
    val creators: LiveData<State<Creator>> = _creators

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        repository.getAllCharacters()
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onGetCharacterSuccess, ::onError)
            .addToCompositeDisposable()
    }

    private fun getAllComics() {
        repository.getAllComics()
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onGetComicsSuccess, ::onError)
            .addToCompositeDisposable()
    }

    private fun getAllCreators() {
        repository.getAllCreators()
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onGetCreatorsSuccess, ::onError)
            .addToCompositeDisposable()
    }

    private fun getAllEvents() {
        repository.getAllEvents()
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onGetEventsSuccess, ::onError)
            .addToCompositeDisposable()
    }

    private fun getAllSeries() {
        repository.getAllSeries()
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onGetSeriesSuccess, ::onError)
            .addToCompositeDisposable()
    }

    private fun getAllStories() {
        repository.getAllStories()
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onGetStoriesSuccess, ::onError)
            .addToCompositeDisposable()
    }

    private fun onGetCharacterSuccess(state: State<Character>) {
        _characters.postValue(state)
    }

    private fun onGetComicsSuccess(state: State<Comic>) {
        _comics.postValue(state)
    }

    private fun onGetCreatorsSuccess(state: State<Creator>) {
        _creators.postValue(state)
    }

    private fun onGetEventsSuccess(state: State<Event>) {
        _events.postValue(state)
    }

    private fun onGetSeriesSuccess(state: State<Series>) {
        _series.postValue(state)
    }

    private fun onGetStoriesSuccess(state: State<Story>) {
        _stories.postValue(state)
    }

    private fun onError(throwable: Throwable) {
        log(throwable.message.toString())
    }

}