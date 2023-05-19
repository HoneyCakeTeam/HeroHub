package com.example.herohub.di

import com.example.herohub.data.local.dao.MarvelDao
import com.example.herohub.data.local.dto_to_entity_mapper.CharacterDtoToCharterEntity
import com.example.herohub.data.local.dto_to_entity_mapper.ComicDtoToComicEntity
import com.example.herohub.data.local.dto_to_entity_mapper.DtoToEntityContainer
import com.example.herohub.data.local.dto_to_entity_mapper.EventMapper
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
        mapperEntityContainer: DtoToEntityContainer,
        apiService: MarvelService,
        marvelDao: MarvelDao
    ): MarvelRepository {
        return MarvelRepositoryImp(mapperEntityContainer, apiService, marvelDao)
    }

    @Singleton
    @Provides
    fun provideMapperContainer(
        characterMapper: CharacterDtoToCharterEntity,
        comicMapper: ComicDtoToComicEntity,
        eventMapper: EventMapper,
        seriesMapper: SeriesMapper,
    ): DtoToEntityContainer =
        DtoToEntityContainer(characterMapper, comicMapper, eventMapper, seriesMapper)

}