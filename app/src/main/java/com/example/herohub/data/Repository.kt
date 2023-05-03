package com.example.herohub.data

import com.example.herohub.data.source.remote.RemoteDataSource
import com.example.herohub.utills.UiState
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class Repository {
    private val remoteDataSource = RemoteDataSource()
    fun getAllCharacters() = wrapWithState {
        remoteDataSource.getAllCharacters()
    }

    fun getCharacterDetails(characterId: Int) =
        wrapWithState { remoteDataSource.getCharacterDetails(characterId) }

    fun getCharacterComics(characterId: Int) =
        wrapWithState { remoteDataSource.getCharacterComics(characterId) }

    private fun <T> wrapWithState(function: () -> Single<Response<T>>): Single<UiState<T>> {
        return function().map {
            try {
                if (it.isSuccessful) {
                    UiState.Success(it.body())
                } else {
                    UiState.Error(it.message())
                }
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Connection Failed")
            }
        }
    }
}