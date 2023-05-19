package com.example.herohub.domain.entity_to_domain_mapper

import com.example.herohub.data.local.SeriesEntity
import com.example.herohub.domain.Mapper
import com.example.herohub.domain.model.Series
import javax.inject.Inject

/**
 * Created by Aziza Helmy on 5/19/2023.
 */
class SeriesEntityToSeries @Inject constructor() : Mapper<List<SeriesEntity>, List<Series>> {
    override fun map(input: List<SeriesEntity>): List<Series> {
        return input.map {
            Series(
                id = it.id,
                title = it.title,
                imageUrl = it.imageUrl,
            )
        }
    }
}