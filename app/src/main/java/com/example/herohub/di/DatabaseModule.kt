package com.example.herohub.di

import android.content.Context
import androidx.room.Room
import com.example.herohub.data.local.MarvelDataBase
import com.example.herohub.data.local.dao.MarvelDao
import com.example.herohub.data.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): MarvelDataBase =
        Room.databaseBuilder(context, MarvelDataBase::class.java, Constant.DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMarvelDao(marvelDataBase: MarvelDataBase): MarvelDao {
        return marvelDataBase.marvelDao()
    }

}