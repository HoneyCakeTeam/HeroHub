package com.example.herohub.domain.mapper.entity_to_domain_mapper

import com.example.herohub.data.local.ComicEntity
import com.example.herohub.domain.mapper.Mapper
import com.example.herohub.domain.model.Comic
import javax.inject.Inject

/**
 * Created by Asia sama on 5/19/2023.
 * sehunexo710@gmail.com
 */
class ComicEntityToComic @Inject constructor() : Mapper<List<ComicEntity>, List<Comic>> {
    override fun map(input: List<ComicEntity>): List<Comic> {
        return input.map {
            Comic(
                id = it.id,
                title = it.title,
                imageUrl = it.imageUrl,
            )
        }
    }
}