package com.example.marvelproject.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.marvelproject.data.repository.Character
import com.example.marvelproject.data.repository.CharacterList

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAllCharacters(characters: List<Character>)

//    @Update
//    suspend fun updateCharacter(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): LiveData<List<Character>>

    // @Query("SELECT * FROM users WHERE id =:id")
      //suspend fun getUser(id: String) : User?
}