package com.example.herohub.model


import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("characters")
    val characters: Result<Item>? = null,
    @SerializedName("comics")
    val comics: Result<Item>? = null,
    @SerializedName("creators")
    val creators: Result<Item>? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("endYear")
    val endYear: Int? = 0,
    @SerializedName("events")
    val events: Result<Item>? = null,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("next")
    val next: Item? = null,
    @SerializedName("previous")
    val previous: Item? = null,
    @SerializedName("rating")
    val rating: String? = "",
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("startYear")
    val startYear: Int? = 0,
    @SerializedName("stories")
    val stories: Result<Item>? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
)