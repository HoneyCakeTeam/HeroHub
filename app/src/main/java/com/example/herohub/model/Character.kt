package com.example.herohub.model


import com.google.gson.annotations.SerializedName


data class Character(
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
):Category {
    override val itemId: Int?
        get() = id
    override val categoryTitle: String?
        get() = name
    override val categoryImage: String?
        get() = thumbnail?.path
}