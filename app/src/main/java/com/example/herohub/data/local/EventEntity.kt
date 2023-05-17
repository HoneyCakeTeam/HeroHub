package com.example.herohub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EVENT_TABLE")
data class EventEntity(
    @PrimaryKey val id:Int,
    val title:String,
    val description: String,
    val modified: String,
    val imageUrl: String
)
