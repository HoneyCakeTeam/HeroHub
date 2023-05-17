package com.example.herohub.domain.model

import com.example.herohub.data.remote.model.CharacterDto

data class Character(
    val description: String? = "",
    val id: Int? = 0,
    val modified: String? = "",
    val name: String? = "",
    val imageUrl: String = "",
)

//dto -> domain
fun CharacterDto.asCharacter(): Character {
    return Character(
        id = id ?: 0,
        name = name ?: "",
        description = description ?: "",
        modified = modified ?: "",
        imageUrl = "${thumbnail?.path}.${thumbnail?.extension}",
    )
}

//entity -> domain
