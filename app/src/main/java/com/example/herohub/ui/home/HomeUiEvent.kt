package com.example.herohub.ui.home

sealed interface HomeUiEvent {
    object ClickSeeAllCharacterEvent : HomeUiEvent
    object ClickSeeAllSeriesEvent : HomeUiEvent
    object ClickSeeAllEventsEvent : HomeUiEvent
    object ClickSeeAllComicsEvent : HomeUiEvent

    data class ClickSliderItemEvent(val id: Int) : HomeUiEvent
    data class ClickMostPopularSeriesItem(val id: Int) : HomeUiEvent
    data class ClickCharacterEvent(val characterId: Int) : HomeUiEvent
    data class ClickEventEvent(val id: Int) : HomeUiEvent
    data class ClickComicEvent(val id: Int) : HomeUiEvent

}