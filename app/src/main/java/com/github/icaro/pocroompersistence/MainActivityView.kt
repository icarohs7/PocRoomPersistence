package com.github.icaro.pocroompersistence

import android.graphics.Color
import android.view.Gravity
import android.view.View
import lk.android.lifecycle.lifecycle
import lk.android.observable.bindString
import lk.anko.adapters.observable.listAdapter
import lk.anko.extensions.verticalRecyclerView
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.button
import org.jetbrains.anko.editText
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.verticalMargin
import org.jetbrains.anko.view
import org.jetbrains.anko.wrapContent

class MainActivityView(private val viewModel: MainActivityViewModel) : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        verticalLayout {
            editText {
                hint = "Person name here"
                bindString(viewModel.personName)
            }
            
            button("Save Person") {
                setOnClickListener { viewModel.savePerson() }
            }
            
            button("Delete Person") {
                setOnClickListener { viewModel.deletePerson() }
            }
            
            button("Erase Database") {
                setOnClickListener { viewModel.eraseDatabase() }
            }
            
            textView("List of registered people") {
                gravity = Gravity.CENTER
                textSize = 20f
            }
            
            view {
                backgroundColor = Color.BLACK
            }.lparams {
                height = 2
                width = matchParent
                verticalMargin = 2
            }
            
            verticalRecyclerView {
                adapter = listAdapter(viewModel.registeredPeople) { person ->
                    
                    textView {
                        gravity = Gravity.CENTER
                        textSize = 16f
                        lifecycle.bind(person) { text = it.name }
                    }.lparams(width = matchParent, height = wrapContent)
                }
            }.lparams(width = matchParent, height = matchParent)
            
        }
    }
}