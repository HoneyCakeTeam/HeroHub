package com.example.herohub.di

import com.example.herohub.data.local.dao.MarvelDao
import com.example.herohub.data.remote.MarvelService
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.data.repository.MarvelRepositoryImp
import com.example.herohub.domain.mapper.CharacterMapper
import com.example.herohub.domain.mapper.ComicMapper
import com.example.herohub.domain.mapper.EventMapper
import com.example.herohub.domain.mapper.MapperContainer
import com.example.herohub.domain.mapper.SearchHistoryMapper
import com.example.herohub.domain.mapper.SeriesMapper
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
        mapperContainer: MapperContainer,
        apiService: MarvelService,
        marvelDao: MarvelDao,
    ): MarvelRepository {
        return MarvelRepositoryImp(mapperContainer, marvelDao, apiService)
    }

    @Singleton
    @Provides
    fun provideMapperContainer(
        characterMapper: CharacterMapper,
        comicMapper: ComicMapper,
        eventMapper: EventMapper,
        seriesMapper: SeriesMapper,
        searchHistoryMapper: SearchHistoryMapper,
    ): MapperContainer = MapperContainer(
        characterMapper,
        comicMapper,
        eventMapper,
        seriesMapper,
        searchHistoryMapper
    )

}