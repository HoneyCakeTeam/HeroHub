package com.example.herohub.di

import com.example.herohub.data.local.dao.MarvelDao
import com.example.herohub.data.local.dto_to_entity_mapper.CharacterDtoToCharterEntity
import com.example.herohub.data.local.dto_to_entity_mapper.ComicDtoToComicEntity
import com.example.herohub.data.local.dto_to_entity_mapper.DtoToEntityContainer
import com.example.herohub.data.local.dto_to_entity_mapper.EventDtoToEventEntity
import com.example.herohub.data.local.dto_to_entity_mapper.SeriesDtoToSeriesEntity
import com.example.herohub.data.remote.MarvelService
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.data.repository.MarvelRepositoryImp
import com.example.herohub.domain.mapper.dto_to_domain_mapper.CharacterDtoToCharacter
import com.example.herohub.domain.mapper.dto_to_domain_mapper.ComicDtoToComic
import com.example.herohub.domain.mapper.dto_to_domain_mapper.DtoToDomainContainer
import com.example.herohub.domain.mapper.dto_to_domain_mapper.EventDtoToEvent
import com.example.herohub.domain.mapper.dto_to_domain_mapper.SeriesDtoToSeries
import com.example.herohub.domain.mapper.entity_to_domain_mapper.CharacterEntityToCharacter
import com.example.herohub.domain.mapper.entity_to_domain_mapper.ComicEntityToComic
import com.example.herohub.domain.mapper.entity_to_domain_mapper.EntityToDomainContainer
import com.example.herohub.domain.mapper.entity_to_domain_mapper.EventEntityToEvent
import com.example.herohub.domain.mapper.entity_to_domain_mapper.SeriesEntityToSeries
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
        dtoToEntityContainer: DtoToEntityContainer,
        entityToDomainContainer: EntityToDomainContainer,
        dtoToDomainContainer: DtoToDomainContainer,
        apiService: MarvelService,
        marvelDao: MarvelDao
    ): MarvelRepository {
        return MarvelRepositoryImp(
            dtoToEntityContainer,
            entityToDomainContainer,
            dtoToDomainContainer,
            apiService,
            marvelDao
        )
    }

    @Singleton
    @Provides
    fun provideDtoToDomainContainer(
        characterMapper: CharacterDtoToCharacter,
        comicMapper: ComicDtoToComic,
        eventMapper: EventDtoToEvent,
        seriesMapper: SeriesDtoToSeries,
    ): DtoToDomainContainer =
        DtoToDomainContainer(characterMapper, comicMapper, eventMapper, seriesMapper)

    @Singleton
    @Provides
    fun provideEntityToDomainContainer(
        characterMapper: CharacterEntityToCharacter,
        comicMapper: ComicEntityToComic,
        eventMapper: EventEntityToEvent,
        seriesMapper: SeriesEntityToSeries,
    ): EntityToDomainContainer =
        EntityToDomainContainer(characterMapper, comicMapper, eventMapper, seriesMapper)

    @Singleton
    @Provides
    fun provideDtoToEntityContainer(
        characterMapper: CharacterDtoToCharterEntity,
        comicMapper: ComicDtoToComicEntity,
        eventMapper: EventDtoToEventEntity,
        seriesMapper: SeriesDtoToSeriesEntity,
    ): DtoToEntityContainer =
        DtoToEntityContainer(characterMapper, comicMapper, eventMapper, seriesMapper)
}