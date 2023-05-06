package com.example.herohub.model


import com.google.gson.annotations.SerializedName

data class Comic(
    @SerializedName("characters")
    val characters: CollectionResult<Item>? = null,
    @SerializedName("collectedIssues")
    val collectedIssues: List<Item>? = listOf(),
    @SerializedName("creators")
    val creators: CollectionResult<Item>? = null,
    @SerializedName("dates")
    val dates: List<Date>? = listOf(),
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("diamondCode")
    val diamondCode: String? = "",
    @SerializedName("digitalId")
    val digitalId: Int? = 0,
    @SerializedName("ean")
    val ean: String? = "",
    @SerializedName("events")
    val events: CollectionResult<Item>? = null,
    @SerializedName("format")
    val format: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("images")
    val images: List<Image?>? = null,
    @SerializedName("isbn")
    val isbn: String? = "",
    @SerializedName("issn")
    val issn: String? = "",
    @SerializedName("issueNumber")
    val issueNumber: Int? = 0,
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("pageCount")
    val pageCount: Int? = 0,
    @SerializedName("prices")
    val prices: List<Price>? = listOf(),
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("series")
    val series: Item? = Item(),
    @SerializedName("stories")
    val stories: CollectionResult<Item>? = null,
    @SerializedName("textObjects")
    val textObjects: List<TextObject>? = listOf(),
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("upc")
    val upc: String? = "",
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
    @SerializedName("variantDescription")
    val variantDescription: String? = "",
    @SerializedName("variants")
    val variants: List<Item>? = listOf(),
) {
    data class Image(
        @SerializedName("extension")
        val extension: String? = null,
        @SerializedName("path")
        val path: String? = null,
    )

    data class Price(
        @SerializedName("price")
        val price: Double? = null,
        @SerializedName("type")
        val type: String? = null,
    )

    data class Date(
        @SerializedName("date")
        val date: String? = null,
        @SerializedName("type")
        val type: String? = null,
    )

    data class TextObject(
        @SerializedName("language")
        val language: String? = null,
        @SerializedName("text")
        val text: String? = null,
        @SerializedName("type")
        val type: String? = null,
    )
}