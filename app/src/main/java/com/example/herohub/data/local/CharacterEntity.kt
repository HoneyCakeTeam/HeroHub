package com.example.herohub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.herohub.domain.model.Character

@Entity(tableName = "CHARACTER_TABLE")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val modified: String,
    val imageUrl: String,
)
fun CharacterEntity.asCharacter(): Character {
    return Character(
        id = id,
        name = title,
        description = description,
        modified = modified,
        imageUrl = imageUrl,
    )
}
