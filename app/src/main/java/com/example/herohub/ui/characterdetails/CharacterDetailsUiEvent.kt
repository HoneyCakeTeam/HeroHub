package com.example.herohub.ui.characterdetails

sealed interface CharacterDetailsUiEvent {

    data class ClickCharacterComic(val id: Int) : CharacterDetailsUiEvent

}