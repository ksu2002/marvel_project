package com.example.marvelproject.data.repository

import androidx.lifecycle.LiveData
import com.example.marvelproject.data.api.ApiHelper
import com.example.marvelproject.data.database.AppDatabase
import com.example.marvelproject.data.model.CharacterId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(
    private val apiHelper: ApiHelper,
    appDatabase: AppDatabase?
) {

    private val characterDao = appDatabase?.characterDao()
    suspend fun getCharacters(): CharacterList? =
        withContext(Dispatchers.IO) {// указала в каком потоке
            val apiResult = apiHelper.getCharacters()//получили из апи
            return@withContext apiResult
        }

    fun getFavorite() = characterDao?.getAllCharacters()

    suspend fun addFavorite(character: Character) =
        withContext(Dispatchers.IO) {
            characterDao?.insertCharacter(character)
        }

    suspend fun getCharacter(characterId: String): CharacterId? = withContext(Dispatchers.IO)
    {
        val apiResultId = apiHelper.getCharacter(characterId)//получили из апи

        return@withContext apiResultId

    }
}