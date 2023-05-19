package com.example.herohub.data.local

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        private const val DATABASE_NAME = "MarvelDataBase"

        @Volatile
        private var instance: MarvelDataBase? = null

        fun getInstance(context: Context): MarvelDataBase {
            return instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }
        }

        private fun buildDatabase(context: Context): MarvelDataBase {
            return Room.databaseBuilder(context, MarvelDataBase::class.java, DATABASE_NAME).build()
        }
    }

}