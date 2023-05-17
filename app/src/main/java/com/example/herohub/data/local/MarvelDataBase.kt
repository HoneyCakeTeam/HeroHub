package com.example.herohub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SeriesEntity::class,
        FavoriteEntity::class,
        EventEntity::class,
        ComicEntity::class,
        CharacterEntity::class],
    version = 1
)
abstract class MarvelDataBase : RoomDatabase() {


}