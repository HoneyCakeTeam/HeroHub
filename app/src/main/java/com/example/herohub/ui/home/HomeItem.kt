package com.example.herohub.ui.home

import com.example.herohub.model.Character
import com.example.herohub.model.Event

sealed class HomeItem(val position: Int) {
    data class Slider(val slider: List<Event>) : HomeItem(0)
    data class SuperHeroes(val superHeroes: List<Character>) : HomeItem(1)
    data class MostPopularCharacters(val mostPopularCharacters: List<Character>) : HomeItem(2)
    data class CharactersByAppearance(val charactersByAppearance: List<Character>) : HomeItem(3)
}