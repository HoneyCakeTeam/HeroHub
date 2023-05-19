package com.example.herohub.data.local.dto_to_entity_mapper

import com.example.herohub.data.local.EventEntity
import com.example.herohub.data.remote.model.EventDto
import com.example.herohub.domain.Mapper
import javax.inject.Inject

/**
 * Created by Aziza Helmy on 5/19/2023.
 */
class EventMapper @Inject constructor() : Mapper<List<EventDto>, List<EventEntity>> {
    override fun map(input: List<EventDto>): List<EventEntity> {
        return input.map {
            EventEntity(
                id = it.id ?: 0,
                title = it.title ?: "",
                description = it.description ?: "",
                modified = it.modified ?: "",
                imageUrl = "${it.thumbnail?.path}.${it.thumbnail?.extension}",
            )
        }
    }
}