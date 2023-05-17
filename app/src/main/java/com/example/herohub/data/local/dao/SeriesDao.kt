package com.example.herohub.data.local.dao

import androidx.room.*
import com.example.herohub.data.local.SeriesEntity
import io.reactivex.rxjava3.core.*

@Dao
interface SeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(Series: SeriesEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSeries(Series: List<SeriesEntity>): Completable

    @Delete
    fun deleteSeries(Series: SeriesEntity): Completable

    @Query("DELETE FROM SERIES_TABLE")
    fun deleteAllSeries(): Completable

    @Update
    fun updateSeries(Series: SeriesEntity): Completable

    @Query("SELECT * FROM SERIES_TABLE")
    fun getAllSeries(): Observable<List<SeriesEntity>>

    @Query("SELECT * FROM SERIES_TABLE WHERE id = :SeriesId")
    fun getSeriesById(SeriesId: Int): Observable<SeriesEntity>
}