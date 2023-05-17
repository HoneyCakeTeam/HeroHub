package com.example.herohub.domin.model

data class FavoriteItem(var id: String, var title: String, var imageUrl: String, var type: Type) {
    enum class Type {
        CHARACTER, EVENT, COMIC, SERIES
    }
}
