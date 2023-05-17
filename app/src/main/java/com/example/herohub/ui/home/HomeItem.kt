package com.example.herohub.ui.home

import com.example.herohub.data.remote.model.Character
import com.example.herohub.data.remote.model.Comic
import com.example.herohub.data.remote.model.Event
import com.example.herohub.data.remote.model.Series

sealed class HomeItem(val position: Int) {
    data class Slider(val slider: List<Event>) : HomeItem(0)
    data class SuperHeroes(val superHeroes: List<Character>) : HomeItem(1)
    data class MostPopularSeries(val mostPopularSeries: List<Series>) : HomeItem(2)
    data class MostPopularEvents(val mostPopularEvents: List<Event>) : HomeItem(3)
    data class MostPopularComics(val mostPopularComics: List<Comic>) : HomeItem(4)
}