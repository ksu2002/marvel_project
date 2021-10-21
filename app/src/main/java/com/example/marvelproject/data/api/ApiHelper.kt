package com.example.marvelproject.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getCharacters() = apiService.getCharacters()
    suspend fun getCharacter(characterId: String) = apiService.getCharacter(characterId)

}