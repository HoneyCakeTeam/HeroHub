package com.example.herohub.ui.home

import com.example.herohub.model.Character
import com.example.herohub.model.Event
import com.example.herohub.model.Comic
import com.example.herohub.model.Series

sealed class HomeItem(val position: Int) {
    data class Slider(val slider: List<Event>) : HomeItem(0)
    data class SuperHeroes(val superHeroes: List<Character>) : HomeItem(1)
    data class MostPopularSeries(val mostPopularSeries: List<Series>) : HomeItem(2)
    data class CharactersByAppearance(val charactersByAppearance: List<Character>) : HomeItem(3)
    data class MostPopularEvents(val mostPopularEvents: List<Event>) : HomeItem(4)
    data class MostPopularComics(val mostPopularComics : List<Comic>) :HomeItem (5)
}