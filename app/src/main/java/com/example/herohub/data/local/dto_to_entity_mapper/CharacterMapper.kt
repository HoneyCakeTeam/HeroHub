package com.example.herohub.data.local.dto_to_entity_mapper

import com.example.herohub.data.local.CharacterEntity
import com.example.herohub.data.remote.model.CharacterDto
import com.example.herohub.domain.Mapper
import javax.inject.Inject

/**
 * Created by Asia sama on 5/19/2023.
 * sehunexo710@gmail.com
 */
class CharacterMapper @Inject constructor() : Mapper<List<CharacterDto>, List<CharacterEntity>> {
    override fun map(input: List<CharacterDto>): List<CharacterEntity> {
        return input.map {
            CharacterEntity(
                id = it.id ?: 0,
                title = it.name ?: "",
                description = it.description ?: "",
                modified = it.modified ?: "",
                imageUrl = "${it.thumbnail?.path}.${it.thumbnail?.extension}",
            )
        }
    }
}