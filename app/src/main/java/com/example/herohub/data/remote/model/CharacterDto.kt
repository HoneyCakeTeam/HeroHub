package com.example.herohub.data.remote.model

import com.example.herohub.data.local.CharacterEntity
import com.example.herohub.domain.model.Character
import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("comics")
    val comics: CollectionResult<Item>? = null,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("events")
    val events: CollectionResult<Item>? = null,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("series")
    val series: CollectionResult<Item>? = null,
    @SerializedName("stories")
    val stories: CollectionResult<Item>?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
)

fun CharacterDto.asCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id ?: 0,
        title = name ?: "",
        description = description ?: "",
        modified = modified ?: "",
        imageUrl = "${thumbnail?.path}.${thumbnail?.extension}"
    )
}

fun CharacterDto.asCharacter(): Character {
    return Character(
        id = id ?: 0,
        name = name ?: "",
        description = description ?: "",
        modified = modified ?: "",
        imageUrl = "${thumbnail?.path}.${thumbnail?.extension}",
    )
}