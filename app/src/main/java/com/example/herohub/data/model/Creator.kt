package com.example.herohub.data.model


import com.google.gson.annotations.SerializedName

data class Creator(
    @SerializedName("comics")
    val comics: CollectionResult<Item>? = null,
    @SerializedName("events")
    val events: CollectionResult<Item>? = null,
    @SerializedName("firstName")
    val firstName: String? = "",
    @SerializedName("fullName")
    val fullName: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("lastName")
    val lastName: String? = "",
    @SerializedName("middleName")
    val middleName: String? = "",
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("series")
    val series: CollectionResult<Item>? = null,
    @SerializedName("stories")
    val stories: CollectionResult<Item>? = null,
    @SerializedName("suffix")
    val suffix: String? = "",
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
)