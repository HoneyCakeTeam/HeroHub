package com.example.herohub.domain.mapper

import javax.inject.Inject

data class MapperContainer @Inject constructor(
    val characterMapper: CharacterMapper,
    val comicMapper: ComicMapper,
    val eventMapper: EventMapper,
    val seriesMapper: SeriesMapper,
    val searchHistoryMapper: SearchHistoryMapper,

    )
