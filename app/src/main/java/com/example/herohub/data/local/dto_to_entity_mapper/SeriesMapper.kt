package com.example.herohub.data.local.dto_to_entity_mapper

import com.example.herohub.data.local.SeriesEntity
import com.example.herohub.data.remote.model.SeriesDto
import javax.inject.Inject

/**
 * Created by Aziza Helmy on 5/19/2023.
 */
class SeriesMapper @Inject constructor() : Mapper<List<SeriesDto>, List<SeriesEntity>> {
    override fun map(input: List<SeriesDto>): List<SeriesEntity> {
        return input.map {
            SeriesEntity(
                id = it.id ?: 0,
                title = it.title ?: "",
                description = it.description ?: "",
                modified = it.modified ?: "",
                imageUrl = "${it.thumbnail?.path}.${it.thumbnail?.extension}",
            )
        }
    }
}