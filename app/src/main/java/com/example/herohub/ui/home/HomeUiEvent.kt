package com.example.herohub.ui.home

sealed interface HomeUiEvent {
    object ClickSeeAllCharacterEvent : HomeUiEvent
    object ClickSeeAllSeriesEvent : HomeUiEvent

    data class ClickMostPopularSeriesItem(val id: Int) : HomeUiEvent
}