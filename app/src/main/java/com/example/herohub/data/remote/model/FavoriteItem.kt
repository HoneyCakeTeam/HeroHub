package com.example.herohub.data.remote.model

data class FavoriteItem(var id: String, var title: String, var imageUrl: String, var type: Type) {
    enum class Type {
        CHARACTER, EVENT, COMIC, SERIES
    }
}