package com.example.herohub.domain.mappers

import com.example.herohub.data.remote.model.ComicDto
import com.example.herohub.domain.model.Comic

class ComicMapper:Mapper<ComicDto , Comic> {
    override fun map(input: ComicDto): Comic {
        return Comic(
            id = input.id,
            title = input.title,
            description = input.description,
            imageUrl = input.thumbnail?.path
        )
    }
}