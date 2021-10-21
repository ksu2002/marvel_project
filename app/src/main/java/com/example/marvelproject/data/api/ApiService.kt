package com.example.marvelproject.data.api

import com.example.marvelproject.data.model.CharacterId
import com.example.marvelproject.data.repository.CharacterList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters?")
    suspend fun getCharacters(@Query("apikey") apikey:String?  = API_KEY, @Query("hash") hash:String?  = HASH,
                              @Query("ts") ts:String?  = TS): CharacterList
    @GET("characters/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId:String, @Query("apikey") apikey:String?  = API_KEY, @Query("hash") hash:String?  = HASH,
                        @Query("ts") ts:String?  = TS) : CharacterId?

    companion object{
        private const val API_KEY="2b985697df3ae8ec2bdd817770973ee5"
        private const val HASH = "b1851f5e0317daf906c9edd25946bf5c"
        private const val TS = "1"
    }

}