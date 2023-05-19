package com.example.herohub.domain.mapper.dto_to_domain_mapper

import javax.inject.Inject

data class DtoToDomainContainer @Inject constructor(
    val characterDtoToCharacter: CharacterDtoToCharacter,
    val comicDtoToComic: ComicDtoToComic,
    val eventDtoToEvent: EventDtoToEvent,
    val seriesDtoToSeries: SeriesDtoToSeries,
)
