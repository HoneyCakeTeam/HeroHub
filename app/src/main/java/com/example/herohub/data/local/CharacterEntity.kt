package com.example.herohub.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.herohub.data.remote.model.Thumbnail

@Entity(tableName = "CHARACTER_TABLE")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val modified: String,
    val imageUrl: String
)
