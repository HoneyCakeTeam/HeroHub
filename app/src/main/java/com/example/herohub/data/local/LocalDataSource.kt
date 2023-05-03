package com.example.herohub.data.local

import com.example.herohub.R
import com.example.herohub.data.local.model.Category

class LocalDataSource {
    private val categories = listOf(
        Category(R.drawable.characters, "Characters"),
        Category(R.drawable.series, "Series"),
        Category(R.drawable.strories, "Stories"),
        Category(R.drawable.comics, "Comics"),
        Category(R.drawable.events, "Events"),
        Category(R.drawable.creators, "Creators"),
    )

    fun getCategories() = categories
}