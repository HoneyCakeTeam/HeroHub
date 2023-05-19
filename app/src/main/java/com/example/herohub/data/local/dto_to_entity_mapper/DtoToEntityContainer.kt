package com.example.herohub.data.local.dto_to_entity_mapper

import javax.inject.Inject

data class DtoToEntityContainer @Inject constructor(
    val characterMapper: CharacterDtoToCharterEntity,
    val comicMapper: ComicDtoToComicEntity,
    val eventMapper: EventMapper,
    val seriesMapper: SeriesMapper,
)
