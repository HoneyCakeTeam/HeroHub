package com.example.herohub.data.local.dao

import androidx.room.*
import com.example.herohub.data.local.*
import io.reactivex.rxjava3.core.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {

    // region Characters
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchKeyword(Keyword: SearchHistoryEntity)

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM CHARACTER_TABLE WHERE title LIKE :query")
    fun getCharactersByName(query: String): Flow<List<CharacterEntity>>
    // endregion

    // region Comics
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllComics(Comics: List<ComicEntity>)

    @Query("SELECT * FROM COMIC_TABLE")
    fun getAllComics(): Flow<List<ComicEntity>>
    // endregion

    // region Events
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEvents(Events: List<EventEntity>)

    @Query("SELECT * FROM EVENT_TABLE")
    fun getAllEvents(): Flow<List<EventEntity>>
    // endregion

    // region Favorite
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(Favorite: FavoriteEntity)

    @Query("SELECT * FROM FAVORITE_TABLE")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun deleteFavorite(Favorite: FavoriteEntity)
    // endregion

    // region Series
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSeries(Series: List<SeriesEntity>)

    @Query("SELECT * FROM SERIES_TABLE")
    fun getAllSeries(): Flow<List<SeriesEntity>>
    // endregion
}