package com.example.herohub.data.repository

import com.example.herohub.domain.model.Character
import com.example.herohub.ui.utils.UiState
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {
    fun getAllCharacters(): Single<UiState<List<Character>>>
}