package com.github.icaro.pocroompersistence.person

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: PersonDatabase? = null
        fun getInstance(context: Context): PersonDatabase {
            return INSTANCE ?: {
                INSTANCE = Room
                    .databaseBuilder(context, PersonDatabase::class.java, "app_database.db")
                    .build()
                INSTANCE!!
            }()
        }
    }

    abstract fun personDao(): PersonDao
}