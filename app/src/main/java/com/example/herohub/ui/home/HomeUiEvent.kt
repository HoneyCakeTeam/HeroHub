package com.example.herohub.ui.home

sealed interface HomeUiEvent {
    object ClickSeeAllCharacterEvent : HomeUiEvent
    object ClickSeeAllEventsEvent : HomeUiEvent

    data class ClickEventEvent(val id: Int) : HomeUiEvent
}