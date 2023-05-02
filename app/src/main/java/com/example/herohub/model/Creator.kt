package com.example.herohub.model


import com.google.gson.annotations.SerializedName

data class Creator(
    @SerializedName("comics")
    val comics: Result<Item>? = null,
    @SerializedName("events")
    val events: Result<Item>? = null,
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
    val series: Result<Item>? = null,
    @SerializedName("stories")
    val stories: Result<Item>? = null,
    @SerializedName("suffix")
    val suffix: String? = "",
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
)