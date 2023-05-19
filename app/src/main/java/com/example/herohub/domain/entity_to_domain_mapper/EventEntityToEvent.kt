package com.example.herohub.domain.dto_to_domain_mapper

import com.example.herohub.data.remote.model.EventDto
import com.example.herohub.domain.Mapper
import com.example.herohub.domain.model.Event
import javax.inject.Inject

/**
 * Created by Aziza Helmy on 5/19/2023.
 */
class EventEntityToEvent @Inject constructor() : Mapper<List<EventDto>, List<Event>> {
    override fun map(input: List<EventDto>): List<Event> {
        return input.map {
            Event(
                id = it.id ?: 0,
                title = it.title ?: "",
                description = it.description ?: "",
                modified = it.modified ?: "",
                imageUrl = "${it.thumbnail?.path}.${it.thumbnail?.extension}",
            )
        }
    }
}