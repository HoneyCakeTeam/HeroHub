package com.example.herohub.domain.entity_to_domain_mapper

import com.example.herohub.data.local.CharacterEntity
import com.example.herohub.domain.Mapper
import com.example.herohub.domain.model.Character
import javax.inject.Inject

/**
 * Created by Asia sama on 5/19/2023.
 * sehunexo710@gmail.com
 */
class CharacterEntityToCharacter @Inject constructor() :
    Mapper<List<CharacterEntity>, List<Character>> {
    override fun map(input: List<CharacterEntity>): List<Character> {
        return input.map {
            Character(
                id = it.id ?: 0,
                name = it.title ?: "",
                imageUrl = it.imageUrl,
            )
        }
    }
}