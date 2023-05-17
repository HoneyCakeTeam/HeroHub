package com.example.herohub.data.remote.model

import com.example.herohub.data.local.FavoriteEntity

data class FavoriteItem(
    var id: String,
    var title: String,
    var imageUrl: String,
    var type: Type,
) {
    enum class Type {
        CHARACTER, EVENT, COMIC, SERIES
    }
}

fun FavoriteEntity.asFavoriteItem(): FavoriteItem {
    return FavoriteItem(
        id = id.toString(),
        title = title,
        imageUrl = imageUrl,
        type = FavoriteItem.Type.valueOf(type)
    )
}

