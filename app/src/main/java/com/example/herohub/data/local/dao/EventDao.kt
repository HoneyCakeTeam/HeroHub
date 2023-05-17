package com.example.herohub.data.local.dao

import androidx.room.*
import com.example.herohub.data.local.EventEntity
import io.reactivex.rxjava3.core.*

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(Event: EventEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllEvents(Events: List<EventEntity>): Completable

    @Delete
    fun deleteEvent(Event: EventEntity): Completable

    @Query("DELETE FROM EVENT_TABLE")
    fun deleteAllEvents(): Completable

    @Update
    fun updateEvent(Event: EventEntity): Completable

    @Query("SELECT * FROM EVENT_TABLE")
    fun getAllEvents(): Observable<List<EventEntity>>

    @Query("SELECT * FROM EVENT_TABLE WHERE id = :EventId")
    fun getEventById(EventId: Int): Observable<EventEntity>
}