package com.example.herohub.domain.mapper

import com.example.herohub.data.remote.model.SeriesDto
import com.example.herohub.domain.model.Series
import javax.inject.Inject

/**
 * Created by Aziza Helmy on 5/19/2023.
 */
class SeriesMapper @Inject constructor() : Mapper<List<SeriesDto>, List<Series>> {
    override fun map(input: List<SeriesDto>): List<Series> {
        return input.map {
            Series(
                id = it.id ?: 0,
                title = it.title ?: "",
                description = it.description ?: "",
                modified = it.modified ?: "",
                imageUrl = "${it.thumbnail?.path}.${it.thumbnail?.extension}",
            )
        }
    }
}