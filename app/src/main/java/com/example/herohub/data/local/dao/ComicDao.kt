package com.example.herohub.data.local.dao

import androidx.room.*
import com.example.herohub.data.local.ComicEntity
import io.reactivex.rxjava3.core.*

@Dao
interface ComicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComic(Comic: ComicEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllComics(Comics: List<ComicEntity>): Completable

    @Delete
    fun deleteComic(Comic: ComicEntity): Completable

    @Query("DELETE FROM COMIC_TABLE")
    fun deleteAllComics(): Completable

    @Update
    fun updateComic(Comic: ComicEntity): Completable

    @Query("SELECT * FROM COMIC_TABLE")
    fun getAllComics(): Observable<List<ComicEntity>>

    @Query("SELECT * FROM COMIC_TABLE WHERE id = :comicId")
    fun getComicById(comicId: Int): Observable<ComicEntity>
}