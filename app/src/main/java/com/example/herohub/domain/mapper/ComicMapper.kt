package com.example.herohub.domain.mapper

import com.example.herohub.data.remote.model.ComicDto
import com.example.herohub.domain.model.Comic

/**
 * Created by Asia sama on 5/19/2023.
 * sehunexo710@gmail.com
 */
class ComicMapper : Mapper<List<ComicDto>, List<Comic>> {
    override fun map(input: List<ComicDto>): List<Comic> {
        return input.map {
            Comic(
                id = it.id ?: 0,
                title = it.title ?: "",
                description = it.description ?: "",
                modified = it.modified ?: "",
                imageUrl = "${it.thumbnail?.path}.${it.thumbnail?.extension}",
            )
        }
    }
}