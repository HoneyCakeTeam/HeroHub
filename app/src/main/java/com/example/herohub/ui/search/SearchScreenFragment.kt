package com.example.herohub.ui.search

import android.icu.util.TimeUnit
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentSearchScreenBinding
import com.example.herohub.ui.base.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchScreenFragment  : BaseFragment<FragmentSearchScreenBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_search_screen
    override val viewModel: SearchViewModel by viewModels()

    override fun setup() {
        viewModel.getAllCharacters()
        search()
    }

    private fun performSearch(query: String) {
       val listOfCharacters =  viewModel.response.value!!.toData()!!.data.results
        listOfCharacters!![0].name

    }

    private fun search() {
        var response = searchObservable
            .debounce(
                500,
                java.util.concurrent.TimeUnit.MILLISECONDS
            )
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { query ->
                performSearch(query)
            }
        response.dispose()
    }

    private val searchObservable = Observable.create { emitter ->
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                emitter.onNext(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                emitter.onNext(query)
                return true
            }
        })
    }

}