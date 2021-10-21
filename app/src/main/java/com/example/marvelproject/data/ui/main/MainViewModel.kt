package com.example.marvelproject.data.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelproject.data.api.ApiHelper
import com.example.marvelproject.data.api.RetrofitBuilder
import com.example.marvelproject.data.database.AppDatabase
import com.example.marvelproject.data.repository.CharacterList
import com.example.marvelproject.data.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val apiHelper = ApiHelper(RetrofitBuilder.apiService)
    private val appDatabase = AppDatabase.getDatabase()
    private val mainRepository: MainRepository = MainRepository(apiHelper, appDatabase)
    private val _characters = MutableLiveData<CharacterList?>(null)
    val characters: LiveData<CharacterList?> = _characters

    init {
        viewModelScope.launch {
            val result = mainRepository.getCharacters()
            _characters.postValue(result)
        }
    }
}