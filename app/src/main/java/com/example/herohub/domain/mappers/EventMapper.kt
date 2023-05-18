package com.example.herohub.domain.mappers

import com.example.herohub.data.remote.model.EventDto
import com.example.herohub.domain.model.Event

class EventMapper:Mapper<EventDto , Event> {
    override fun map(input: EventDto): Event {
        return Event(
            id = input.id,
            title = input.title,
            description = input.description,
            imageUrl = input.thumbnail?.path
        )
    }
}