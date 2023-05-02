package com.example.herohub.model


import com.google.gson.annotations.SerializedName


data class Character(
    @SerializedName("comics")
    val comics: Result<Item>? = null,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("events")
    val events: Result<Item>? = null,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("series")
    val series: Result<Item>? = null,
    @SerializedName("stories")
    val stories: Result<Item>?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
)