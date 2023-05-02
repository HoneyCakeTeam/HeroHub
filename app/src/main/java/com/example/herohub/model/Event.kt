package com.example.herohub.model


import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("characters")
    val characters: Result<Item>? = null,
    @SerializedName("comics")
    val comics: Result<Item>? = null,
    @SerializedName("creators")
    val creators: Result<Item>? = null,
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
    val series: Result<Item>? = null,
    @SerializedName("start")
    val start: String? = "",
    @SerializedName("stories")
    val stories: Result<Item>? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
)