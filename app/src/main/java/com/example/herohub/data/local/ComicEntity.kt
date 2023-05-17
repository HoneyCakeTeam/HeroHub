package com.example.herohub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.herohub.domain.model.Comic

@Entity(tableName = "COMIC_TABLE")
data class ComicEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val modified: String,
    val imageUrl: String
)
fun ComicEntity.asComic(): Comic {
    return Comic(
        id = id,
        title = title,
        description = description,
        modified = modified,
        imageUrl = imageUrl,
    )
}
