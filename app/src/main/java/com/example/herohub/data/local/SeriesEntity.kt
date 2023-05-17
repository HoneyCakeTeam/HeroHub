package com.example.herohub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.herohub.domain.model.Series

@Entity(tableName = "SERIES_TABLE")
data class SeriesEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val modified: String,
    val imageUrl: String
)
fun SeriesEntity.asSeries(): Series {
    return Series(
        id = id,
        title = title,
        description = description,
        modified = modified,
        imageUrl = imageUrl,
    )
}
