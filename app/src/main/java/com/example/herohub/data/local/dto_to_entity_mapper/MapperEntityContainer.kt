package com.example.herohub.data.local.dto_to_entity_mapper

import javax.inject.Inject

data class MapperEntityContainer @Inject constructor(
    val characterMapper: CharacterMapper,
    val comicMapper: ComicMapper,
    val eventMapper: EventMapper,
    val seriesMapper: SeriesMapper,
)
