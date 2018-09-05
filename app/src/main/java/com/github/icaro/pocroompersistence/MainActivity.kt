package com.github.icaro.pocroompersistence

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.icaro.pocroompersistence.person.PersonDatabase
import org.jetbrains.anko.setContentView
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainActivityViewModel(WeakReference(this))
        val dao = PersonDatabase.getInstance(this).personDao()
        dao.queryAllWithObservable().observe(this, Observer {
            viewModel.registeredPeople.clear()
            viewModel.registeredPeople.addAll(it!!.asIterable())
        })
        MainActivityView(viewModel).setContentView(this)
    }
}
