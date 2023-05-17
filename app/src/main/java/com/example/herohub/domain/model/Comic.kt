package com.example.herohub.domain.model

import com.example.herohub.data.remote.model.ComicDto

data class Comic(
    val description: String = "",
    val id: Int = 0,
    val modified: String = "",
    val imageUrl: String = "",
    val title: String = "",
)
//dto -> domain

fun ComicDto.asComic(): Comic {
    return Comic(
        id = id ?: 0,
        title = title ?: "",
        description = description ?: "",
        modified = modified ?: "",
        imageUrl = "${thumbnail?.path}.${thumbnail?.extension}",
    )
}

//entity -> domain

