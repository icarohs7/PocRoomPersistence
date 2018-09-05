package com.github.icaro.pocroompersistence.person

import android.arch.persistence.room.*

@Entity(tableName = "person")
data class Person(
    @PrimaryKey
    val name: String
)