package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.intentosraros.R.id.toolbar

class MarvelHistoriaActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_historia)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.marvel)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_agregar){
            val intentListado = Intent(this, ListadoPeliculasActivity::class.java)
            startActivity(intentListado)
        }else if(item.itemId == R.id.item_personajes){
            val intentListado = Intent(this, PersonajesActivity::class.java)
            startActivity(intentListado)
        }
        return super.onOptionsItemSelected(item)
    }



}