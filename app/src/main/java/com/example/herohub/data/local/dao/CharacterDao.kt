package com.example.herohub.data.local.dao

import androidx.room.*
import com.example.herohub.data.local.CharacterEntity
import io.reactivex.rxjava3.core.*

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCharacters(characters: List<CharacterEntity>): Completable

    @Delete
    fun deleteCharacter(character: CharacterEntity): Completable

    @Query("DELETE FROM CHARACTER_TABLE")
    fun deleteAllCharacters(): Completable

    @Update
    fun updateCharacter(character: CharacterEntity): Completable

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCharacters(): Observable<List<CharacterEntity>>

    @Query("SELECT * FROM CHARACTER_TABLE WHERE id = :characterId")
    fun getCharacterById(characterId: Int): Observable<CharacterEntity>

    @Query("SELECT * FROM CHARACTER_TABLE WHERE title LIKE :query")
    fun getCharactersByName(query: String): Observable<List<CharacterEntity>>
}