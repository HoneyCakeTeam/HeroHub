package com.example.herohub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FAVORITE_TABLE")
data class FavoriteEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val imageUrl: String,
    val type: String,
)

