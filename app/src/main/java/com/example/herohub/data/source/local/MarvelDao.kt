package com.example.herohub.data.source.local

import androidx.room.*
import com.example.herohub.data.source.local.*
import io.reactivex.rxjava3.core.*

@Dao
interface MarvelDao {

    // region Characters
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCharacters(characters: List<CharacterEntity>): Completable

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCharacters(): Observable<List<CharacterEntity>>

    @Query("SELECT * FROM CHARACTER_TABLE WHERE title LIKE :query")
    fun getCharactersByName(query: String): Observable<List<CharacterEntity>>
    // endregion

    // region Comics
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllComics(Comics: List<ComicEntity>): Completable

    @Query("SELECT * FROM COMIC_TABLE")
    fun getAllComics(): Observable<List<ComicEntity>>
    // endregion

    // region Events
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllEvents(Events: List<EventEntity>): Completable

    @Query("SELECT * FROM EVENT_TABLE")
    fun getAllEvents(): Observable<List<EventEntity>>
    // endregion

    // region Favorite
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(Favorite: FavoriteEntity): Completable

    @Query("SELECT * FROM FAVORITE_TABLE")
    fun getAllFavorites(): Observable<List<FavoriteEntity>>

    @Delete
    fun deleteFavorite(Favorite: FavoriteEntity): Completable
    // endregion

    // region Series
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSeries(Series: List<SeriesEntity>): Completable

    @Query("SELECT * FROM SERIES_TABLE")
    fun getAllSeries(): Observable<List<SeriesEntity>>
    // endregion
}