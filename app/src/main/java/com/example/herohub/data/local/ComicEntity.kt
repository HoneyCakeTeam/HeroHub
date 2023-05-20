package com.example.herohub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COMIC_TABLE")
data class ComicEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val modified: String,
    val imageUrl: String
)