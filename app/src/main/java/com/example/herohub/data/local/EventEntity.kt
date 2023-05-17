package com.example.herohub.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.herohub.data.remote.model.Thumbnail

@Entity(tableName = "EVENT_TABLE")
data class EventEntity(
    @PrimaryKey val id:Int,
    val title:String,
    val description: String,
    val modified: String,
    val imageUrl: String
)
