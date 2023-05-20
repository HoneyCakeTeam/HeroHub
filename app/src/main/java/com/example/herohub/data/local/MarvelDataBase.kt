package com.example.herohub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.herohub.data.local.dao.MarvelDao

@Database(
    entities = [
        SeriesEntity::class,
        FavoriteEntity::class,
        EventEntity::class,
        ComicEntity::class,
        CharacterEntity::class,
        SearchHistoryEntity::class
    ],
    version = 1
)
abstract class MarvelDataBase : RoomDatabase() {
    abstract fun marvelDao(): MarvelDao

}