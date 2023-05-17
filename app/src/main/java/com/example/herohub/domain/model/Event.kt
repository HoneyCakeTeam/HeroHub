package com.example.herohub.domain.model

import com.example.herohub.data.local.EventEntity

data class Event(
    val description: String = "",
    val id: Int? = 0,
    val modified: String = "",
    val imageUrl: String = "",
    val title: String = "",

    )

//entity -> domain
fun EventEntity.asEvent(): Event {
    return Event(
        id = id,
        title = title,
        description = description,
        modified = modified,
        imageUrl = imageUrl,
    )
}

//dto -> domain
