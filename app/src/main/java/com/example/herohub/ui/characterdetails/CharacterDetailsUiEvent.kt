package com.example.herohub.ui.characterdetails

sealed interface CharacterDetailsUiEvent {

    data class ClickCharacterComic(val id: Int) : CharacterDetailsUiEvent
    data class ClickCharacterSeries(val id: Int) : CharacterDetailsUiEvent
    data class ClickCharacterEvents(val id: Int) : CharacterDetailsUiEvent

}