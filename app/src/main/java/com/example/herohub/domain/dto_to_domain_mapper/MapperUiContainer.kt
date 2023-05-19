package com.example.herohub.domain.dto_to_domain_mapper

import javax.inject.Inject

data class MapperUiContainer @Inject constructor(
    val characterDtoToCharacter: CharacterDtoToCharacter,
    val comicDtoToComic: ComicDtoToComic,
    val eventDtoToEvent: EventDtoToEvent,
    val seriesDtoToSeries: SeriesDtoToSeries,
)
