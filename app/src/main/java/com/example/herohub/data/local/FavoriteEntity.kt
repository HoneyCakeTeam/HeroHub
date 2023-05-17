package com.example.herohub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.herohub.domain.model.FavoriteItem

@Entity(tableName = "FAVORITE_TABLE")
data class FavoriteEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val imageUrl: String,
    val type: String,
)
fun FavoriteEntity.asFavoriteItem(): FavoriteItem {
    return FavoriteItem(
        id = id.toString(),
        title = title,
        imageUrl = imageUrl,
        type = FavoriteItem.Type.valueOf(type)
    )
}

