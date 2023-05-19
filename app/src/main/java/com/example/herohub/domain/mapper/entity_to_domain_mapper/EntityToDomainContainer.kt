package com.example.herohub.domain.mapper.entity_to_domain_mapper

import javax.inject.Inject

data class EntityToDomainContainer @Inject constructor(
    val characterMapper: CharacterEntityToCharacter,
    val comicMapper: ComicEntityToComic,
    val eventMapper: EventEntityToEvent,
    val seriesMapper: SeriesEntityToSeries,
)
