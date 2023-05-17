package com.example.herohub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.herohub.domain.model.Event

@Entity(tableName = "EVENT_TABLE")
data class EventEntity(
    @PrimaryKey val id:Int,
    val title:String,
    val description: String,
    val modified: String,
    val imageUrl: String
)

//entity -> domain
fun EventEntity.asEvent(): Event {
    return Event(
        id = id,
        title = title,
        description = description,
        modified = modified,
        imageUrl = imageUrl,
    )
}
