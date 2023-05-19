package com.example.herohub.domain.mapper.dto_to_domain_mapper

import com.example.herohub.data.remote.model.ComicDto
import com.example.herohub.domain.mapper.Mapper
import com.example.herohub.domain.model.Comic
import javax.inject.Inject

/**
 * Created by Asia sama on 5/19/2023.
 * sehunexo710@gmail.com
 */
class ComicDtoToComic @Inject constructor() : Mapper<List<ComicDto>, List<Comic>> {
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