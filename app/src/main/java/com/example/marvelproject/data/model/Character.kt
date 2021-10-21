package com.example.marvelproject.data.repository

import androidx.room.*
import com.google.gson.annotations.SerializedName


data class CharacterList(
    val data: Data
)

data class Data(
    val results: List<Character>
)

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: String,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,
    @Embedded
    @SerializedName("thumbnail")
    var image: Thumbnail?
) {
}

data class Thumbnail(
    val path: String?,
    val extension: String?
)