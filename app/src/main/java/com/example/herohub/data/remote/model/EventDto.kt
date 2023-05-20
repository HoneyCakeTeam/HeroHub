package com.example.herohub.data.remote.model

import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("characters")
    val characters: CollectionResult<Item>? = null,
    @SerializedName("comics")
    val comics: CollectionResult<Item>? = null,
    @SerializedName("creators")
    val creators: CollectionResult<Item>? = null,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("end")
    val end: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("next")
    val next: Item? = null,
    @SerializedName("previous")
    val previous: Item? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("series")
    val series: CollectionResult<Item>? = null,
    @SerializedName("start")
    val start: String? = "",
    @SerializedName("stories")
    val stories: CollectionResult<Item>? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
)
