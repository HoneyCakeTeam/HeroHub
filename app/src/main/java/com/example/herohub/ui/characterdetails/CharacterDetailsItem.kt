package com.example.herohub.ui.characterdetails

import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import com.example.herohub.model.Event
import com.example.herohub.model.Series

/**
 * Created by Aziza Helmy on 5/11/2023.
 */
sealed class CharacterDetailsItem(val position: Int) {

    data class CharacterInfo(val character: Character) : CharacterDetailsItem(0)
    data class CharacterEvents(val events: List<Event>) : CharacterDetailsItem(1)
    data class CharacterComics(val comics: List<Comic>) : CharacterDetailsItem(2)
    data class CharacterSeries(val series: List<Series>) : CharacterDetailsItem(3)
}
