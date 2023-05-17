package com.example.herohub.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object{
        private const val DATABASE_NAME ="MarvelDataBase"
        @Volatile private var instance : MarvelDataBase? = null


        private fun buildDatabase(context: Context) :MarvelDataBase{
            return Room.databaseBuilder(context ,MarvelDataBase::class.java , DATABASE_NAME).build()
        }
    }
}