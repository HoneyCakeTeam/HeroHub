package com.example.herohub.ui.home

sealed interface HomeUiEvent {
    object ClickSeeAllCharacterEvent : HomeUiEvent
    object ClickSeeAllEventsEvent : HomeUiEvent
    object ClickSeeAllComicsEvent : HomeUiEvent

    data class ClickEventEvent(val id: Int) : HomeUiEvent
    data class ClickComicEvent(val id: Int) : HomeUiEvent
}