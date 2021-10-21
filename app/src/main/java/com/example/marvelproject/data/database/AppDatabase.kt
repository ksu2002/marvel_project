package com.example.marvelproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marvelproject.data.repository.Character
import com.example.marvelproject.data.repository.CharacterList

@Database(entities = [Character::class], version = 1)//указывает сущности, создает таблицу под user
abstract class AppDatabase: RoomDatabase() {

    abstract fun characterDao() : CharacterDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "course"

        fun invoke(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room
                        .databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                }
            }

            return INSTANCE!!
        }

        fun getDatabase() = INSTANCE
    }

}