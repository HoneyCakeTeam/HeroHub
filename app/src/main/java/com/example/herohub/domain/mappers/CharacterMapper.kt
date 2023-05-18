package com.example.herohub.domain.mappers

import com.example.herohub.data.remote.model.CharacterDto
import com.example.herohub.domain.model.Character

class CharacterMapper:Mapper<CharacterDto , Character> {
    override fun map(input: CharacterDto): Character {
        return Character(
            id = input.id,
            name = input.name,
            description = input.description,
            imageUrl = input.thumbnail?.path.toString()
        )
    }
}