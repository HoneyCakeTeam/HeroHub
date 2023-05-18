package com.example.herohub.di

import com.example.herohub.data.remote.MarvelService
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.data.repository.MarvelRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {

    @Provides
    fun provideMarvelRepository(marvelService: MarvelService): MarvelRepository {
        return MarvelRepositoryImpl(marvelService)
    }
}