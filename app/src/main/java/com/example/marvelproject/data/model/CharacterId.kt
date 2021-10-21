package com.example.marvelproject.data.model

import com.example.marvelproject.data.repository.Thumbnail
import com.google.gson.annotations.SerializedName


data class CharacterId(
    val data: Data
)

data class Data(
    val results: List<CharacterInfo>
)

data class ComicsItem(
    @SerializedName("name")
    val comicsName: String?
)

data class CharacterInfo(
    @SerializedName("id")
    val id: String,
    val name: String?,
    val description: String?,
    @SerializedName("thumbnail")
    val image: Thumbnail?,
    val comics: Comics
)


data class Comics(
    val items: List<ComicsItem>
)