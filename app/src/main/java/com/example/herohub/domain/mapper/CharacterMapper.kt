package com.example.herohub.domain.mapper

import com.example.herohub.data.remote.model.CharacterDto
import com.example.herohub.domain.model.Character
import javax.inject.Inject

/**
 * Created by Asia sama on 5/19/2023.
 * sehunexo710@gmail.com
 */
class CharacterMapper @Inject constructor() : Mapper<List<CharacterDto>, List<Character>> {
    override fun map(input: List<CharacterDto>): List<Character> {
        return input.map {
            Character(
                id = it.id ?: 0,
                name = it.name ?: "",
                description = it.description ?: "",
                modified = it.modified ?: "",
                imageUrl = "${it.thumbnail?.path}.${it.thumbnail?.extension}",
            )
        }
    }
}