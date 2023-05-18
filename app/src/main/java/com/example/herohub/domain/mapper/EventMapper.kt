package com.example.herohub.domain.mapper

import com.example.herohub.data.remote.model.EventDto
import com.example.herohub.domain.model.Event

/**
 * Created by Aziza Helmy on 5/19/2023.
 */
class EventMapper : Mapper<List<EventDto>, List<Event>> {
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