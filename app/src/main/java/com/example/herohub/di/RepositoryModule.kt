package com.example.herohub.di

import com.example.herohub.data.local.dao.MarvelDao
import com.example.herohub.data.local.dto_to_entity_mapper.CharacterMapper
import com.example.herohub.data.local.dto_to_entity_mapper.ComicMapper
import com.example.herohub.data.local.dto_to_entity_mapper.EventMapper
import com.example.herohub.data.local.dto_to_entity_mapper.MapperEntityContainer
import com.example.herohub.data.local.dto_to_entity_mapper.SeriesMapper
import com.example.herohub.data.remote.MarvelService
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.data.repository.MarvelRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMarvelRepository(
        mapperEntityContainer: MapperEntityContainer,
        apiService: MarvelService,
        marvelDao: MarvelDao
    ): MarvelRepository {
        return MarvelRepositoryImp(mapperEntityContainer, apiService, marvelDao)
    }

    @Singleton
    @Provides
    fun provideMapperContainer(
        characterMapper: CharacterMapper,
        comicMapper: ComicMapper,
        eventMapper: EventMapper,
        seriesMapper: SeriesMapper,
    ): MapperEntityContainer =
        MapperEntityContainer(characterMapper, comicMapper, eventMapper, seriesMapper)

}