package com.example.herohub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SEARCH_HISTORY_TABLE")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long =0,
    val name: String
)