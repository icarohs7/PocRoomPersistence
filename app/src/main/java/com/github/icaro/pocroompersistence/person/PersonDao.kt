package com.github.icaro.pocroompersistence.person

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface PersonDao {

    @Query("SELECT * FROM person WHERE name = :personName")
    fun query(personName: String): Person

    @Query("SELECT * FROM person")
    fun queryAll(): List<Person>

    @Query("SELECT * FROM person")
    fun queryAllWithObservable(): LiveData<List<Person>>

    @Insert
    fun insert(person: Person)

    @Update
    fun update(person: Person)

    @Delete
    fun delete(person: Person)

    @Query("DELETE FROM person WHERE name = :personName")
    fun deleteByName(personName: String)

    @Query("DELETE FROM person")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM person")
    fun getCount(): Int
}