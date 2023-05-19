package com.example.herohub.domain.mapper

import com.example.herohub.data.local.SearchHistoryEntity
import com.example.herohub.domain.model.SearchHistory
import javax.inject.Inject

/**
 * Created by Asia sama on 5/19/2023.
 * sehunexo710@gmail.com
 */
class SearchHistoryMapper @Inject constructor() :
    Mapper<List<SearchHistoryEntity>, List<SearchHistory>> {
    override fun map(input: List<SearchHistoryEntity>): List<SearchHistory> {
        return input.map {
            SearchHistory(
                id = it.id,
                name = it.name
            )
        }
    }
}