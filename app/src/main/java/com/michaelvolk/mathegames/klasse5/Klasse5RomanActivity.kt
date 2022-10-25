package com.michaelvolk.mathegames.klasse5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.michaelvolk.mathegames.R

class Klasse5RomanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_klasse5_roman)
    }

    // Wenn der Menüknopf "zurück" geklickt wird, schließt die Activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.finish()
        return true
    }
}