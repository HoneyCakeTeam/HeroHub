package com.example.herohub.data.remote.model


import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("characters")
    val characters: CollectionResult<Item>? = null,
    @SerializedName("comics")
    val comics: CollectionResult<Item>? = null,
    @SerializedName("creators")
    val creators: CollectionResult<Item>? = null,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("events")
    val events: CollectionResult<Item>? = null,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("originalIssue")
    val originalIssue: Item? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("series")
    val series: CollectionResult<Item>? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("type")
    val type: String? = "",
)