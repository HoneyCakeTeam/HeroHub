package com.example.herohub.data.local.dao

import androidx.room.*
import com.example.herohub.data.local.FavoriteEntity
import io.reactivex.rxjava3.core.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(Favorite: FavoriteEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFavorites(Favorites: List<FavoriteEntity>): Completable

    @Delete
    fun deleteFavorite(Favorite: FavoriteEntity): Completable

    @Query("DELETE FROM FAVORITE_TABLE")
    fun deleteAllFavorites(): Completable

    @Update
    fun updateFavorite(Favorite: FavoriteEntity): Completable

    @Query("SELECT * FROM FAVORITE_TABLE")
    fun getAllFavorites(): Observable<List<FavoriteEntity>>

    @Query("SELECT * FROM FAVORITE_TABLE WHERE id = :favoriteId")
    fun getFavoriteById(favoriteId: Int): Observable<FavoriteEntity>
}