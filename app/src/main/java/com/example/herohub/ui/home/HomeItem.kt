package com.example.herohub.ui.home

import com.example.herohub.model.Character

sealed class HomeItem(val position: Int) {
    data class CharactersByAppearance(val slider: List<Character>) : HomeItem(3)
    data class SuperHeroes(val superHeroes: List<Character>) : HomeItem(1)
    data class MostPopularCharacters(val mostPopularCharacters: List<Character>) : HomeItem(2)
}