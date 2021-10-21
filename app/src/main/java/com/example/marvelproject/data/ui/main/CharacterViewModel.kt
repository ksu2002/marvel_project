package com.example.marvelproject.data.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelproject.data.api.ApiHelper
import com.example.marvelproject.data.api.RetrofitBuilder
import com.example.marvelproject.data.database.AppDatabase
import com.example.marvelproject.data.model.CharacterId
import com.example.marvelproject.data.repository.Character
import com.example.marvelproject.data.repository.MainRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    private val apiHelper = ApiHelper(RetrofitBuilder.apiService)
    private val appDatabase = AppDatabase.getDatabase()
    private val mainRepository: MainRepository = MainRepository(apiHelper, appDatabase)
    private val _character = MutableLiveData<CharacterId?>(null)
    val character: LiveData<CharacterId?> = _character

    fun addFavorite(character: Character) = viewModelScope.launch {
        mainRepository.addFavorite(character)
    }

    fun getFavorites() = mainRepository.getFavorite()

    fun loadCharacterInfo(characterId: String) = viewModelScope.launch {
        val result = mainRepository.getCharacter(characterId)
        _character.postValue(result)
    }
}