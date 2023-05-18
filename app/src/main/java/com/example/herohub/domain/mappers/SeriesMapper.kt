package com.example.herohub.domain.mappers

import com.example.herohub.data.remote.model.SeriesDto
import com.example.herohub.domain.model.Series

class SeriesMapper:Mapper<SeriesDto , Series> {
    override fun map(input: SeriesDto): Series {
        return Series(
            id = input.id,
            title = input.title,
            description = input.description,
            imageUrl = input.thumbnail?.path
        )
    }
}