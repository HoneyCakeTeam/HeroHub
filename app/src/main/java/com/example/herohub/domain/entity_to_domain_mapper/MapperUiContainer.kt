package com.example.herohub.domain.entity_to_domain_mapper

import com.example.herohub.data.local.dto_to_entity_mapper.CharacterMapper
import com.example.herohub.data.local.dto_to_entity_mapper.ComicMapper
import com.example.herohub.data.local.dto_to_entity_mapper.EventMapper
import com.example.herohub.data.local.dto_to_entity_mapper.SeriesMapper
import javax.inject.Inject

data class MapperUiContainer @Inject constructor(
    val characterMapper: CharacterMapper,
    val comicMapper: ComicMapper,
    val eventMapper: EventMapper,
    val seriesMapper: SeriesMapper,
)
