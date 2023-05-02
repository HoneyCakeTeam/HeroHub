package com.example.herohub

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {

        compositeDisposable.dispose()
        super.onCleared()
    }
}