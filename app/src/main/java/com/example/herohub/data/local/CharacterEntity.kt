package com.example.herohub.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.herohub.data.remote.model.Thumbnail

@Entity(tableName = "CHARACTER_TABLE")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    @ColumnInfo("imagePath") val thumbnail: Thumbnail
)
