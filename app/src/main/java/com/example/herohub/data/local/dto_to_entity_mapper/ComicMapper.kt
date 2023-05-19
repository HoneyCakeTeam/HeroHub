package com.example.herohub.data.local.dto_to_entity_mapper

import com.example.herohub.data.local.ComicEntity
import com.example.herohub.data.remote.model.ComicDto
import javax.inject.Inject

/**
 * Created by Asia sama on 5/19/2023.
 * sehunexo710@gmail.com
 */
class ComicMapper @Inject constructor() : Mapper<List<ComicDto>, List<ComicEntity>> {
    override fun map(input: List<ComicDto>): List<ComicEntity> {
        return input.map {
            ComicEntity(
                id = it.id ?: 0,
                title = it.title ?: "",
                description = it.description ?: "",
                modified = it.modified ?: "",
                imageUrl = "${it.thumbnail?.path}.${it.thumbnail?.extension}",
            )
        }
    }
}