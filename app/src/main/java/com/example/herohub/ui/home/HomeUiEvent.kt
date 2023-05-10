package com.example.herohub.ui.home

sealed interface HomeUiEvent {
    object ClickSeeAllCharactersEvent : HomeUiEvent
    object ClickSeeAllComicsEvent : HomeUiEvent
    object ClickSeeAllEventsEvent : HomeUiEvent
    object ClickSeeAllSeriesEvent : HomeUiEvent

    data class ClickCharacterEvent(val id: Int) : HomeUiEvent
    data class ClickComicEvent(val id: Int) : HomeUiEvent
    data class ClickEventEvent(val id: Int) : HomeUiEvent
    data class ClickSeriesEvent(val id: Int) : HomeUiEvent

}