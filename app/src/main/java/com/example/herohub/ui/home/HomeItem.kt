package com.example.herohub.ui.home

import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import com.example.herohub.model.Series

sealed class HomeItem(val position: Int) {
    data class Slider(val slider: List<Series>) : HomeItem(0)
    data class SuperHeroes(val superHeroes: List<Character>) : HomeItem(1)
    data class MostPopularCharacters(val mostPopularComics: List<Comic>) : HomeItem(2)
}