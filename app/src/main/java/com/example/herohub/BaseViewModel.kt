package com.example.herohub

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        Log.i("BaseViewModel", "on clear")
        compositeDisposable.dispose()
        super.onCleared()
    }
}