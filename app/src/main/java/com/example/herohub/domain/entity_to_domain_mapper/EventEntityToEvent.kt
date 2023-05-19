package com.example.herohub.domain.entity_to_domain_mapper

import com.example.herohub.data.local.EventEntity
import com.example.herohub.domain.Mapper
import com.example.herohub.domain.model.Event
import javax.inject.Inject

/**
 * Created by Aziza Helmy on 5/19/2023.
 */
class EventEntityToEvent @Inject constructor() : Mapper<List<EventEntity>, List<Event>> {
    override fun map(input: List<EventEntity>): List<Event> {
        return input.map {
            Event(
                id = it.id,
                title = it.title,
                imageUrl = it.imageUrl,
            )
        }
    }
}