package com.example.herohub.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.herohub.data.local.dao.MarvelDao

@Database(
    entities = [
        SeriesEntity::class,
        FavoriteEntity::class,
        EventEntity::class,
        ComicEntity::class,
        CharacterEntity::class
    ],
    version = 2,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 1, to = 2)]
)
abstract class MarvelDataBase : RoomDatabase() {
    abstract fun marvelDao(): MarvelDao

}