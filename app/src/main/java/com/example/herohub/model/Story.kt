package com.example.herohub.model


import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("characters")
    val characters: Result<Item>? = null,
    @SerializedName("comics")
    val comics: Result<Item>? = null,
    @SerializedName("creators")
    val creators: Result<Item>? = null,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("events")
    val events: Result<Item>? = null,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("originalIssue")
    val originalIssue: Item? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("series")
    val series: Result<Item>? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("type")
    val type: String? = "",
)