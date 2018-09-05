package com.github.icaro.pocroompersistence

import android.content.Context
import com.github.icaro.pocroompersistence.person.Person
import com.github.icaro.pocroompersistence.person.PersonDao
import com.github.icaro.pocroompersistence.person.PersonDatabase
import kotlinx.coroutines.experimental.Deferred
import lk.kotlin.observable.list.observableListOf
import lk.kotlin.observable.property.StandardObservableProperty
import org.jetbrains.anko.coroutines.experimental.bg
import java.lang.ref.WeakReference

class MainActivityViewModel(private val context: WeakReference<Context>) {
    var personName = StandardObservableProperty("")
    var registeredPeople = observableListOf<Person>()
    
    init {
        dbTransactionAsync {
            registeredPeople.addAll(it.queryAll())
        }
    }
    
    private val personDao: PersonDao? by lazy {
        context.get()?.let { ctx ->
            PersonDatabase.getInstance(ctx).personDao()
        }
    }
    
    private fun <T> dbTransactionAsync(fn: (personDao: PersonDao) -> T): Deferred<T?> {
        return bg {
            personDao?.let(fn)
        }
    }
    
    fun savePerson() {
        dbTransactionAsync { dao ->
            dao.insert(Person(personName.value))
        }
    }
    
    fun deletePerson() {
        dbTransactionAsync { dao ->
            dao.deleteByName(personName.value)
        }
    }
    
    fun eraseDatabase() {
        dbTransactionAsync { dao ->
            dao.deleteAll()
        }
    }
}