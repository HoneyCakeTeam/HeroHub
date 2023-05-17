package com.example.herohub.data.remote.model

import com.example.herohub.data.local.SeriesEntity
import com.example.herohub.domain.model.Series
import com.google.gson.annotations.SerializedName

data class SeriesDto(
    @SerializedName("characters")
    val characters: CollectionResult<Item>? = null,
    @SerializedName("comics")
    val comics: CollectionResult<Item>? = null,
    @SerializedName("creators")
    val creators: CollectionResult<Item>? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("endYear")
    val endYear: Int? = 0,
    @SerializedName("events")
    val events: CollectionResult<Item>? = null,
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
    val stories: CollectionResult<Item>? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
)
fun SeriesDto.asSeriesEntity(): SeriesEntity {
    return SeriesEntity(
        id = id ?: 0,
        title = title ?: "",
        description = description ?: "",
        modified = modified ?: "",
        imageUrl = "${thumbnail?.path}.${thumbnail?.extension}"
    )
}
fun SeriesDto.asSeries(): Series {
    return Series(
        id = id ?: 0,
        title = title ?: "",
        description = description ?: "",
        modified = modified ?: "",
        imageUrl = "${thumbnail?.path}.${thumbnail?.extension}",
    )
}