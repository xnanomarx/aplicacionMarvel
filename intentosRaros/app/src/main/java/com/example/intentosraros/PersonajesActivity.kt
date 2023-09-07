package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class PersonajesActivity : AppCompatActivity() {

    lateinit var botonHeroes: Button
    lateinit var botonVillanos: Button
    lateinit var botonAntiheroes: Button
    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personajes)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)


        botonHeroes = findViewById(R.id.btnHeroes)
        botonHeroes.setOnClickListener{
            val intentHeroes = Intent(this,heroes::class.java)
            startActivity(intentHeroes)
        }

        botonVillanos = findViewById(R.id.btnVillanos)
        botonVillanos.setOnClickListener{
            val intentVillanos = Intent(this,villanos::class.java)
            startActivity(intentVillanos)
        }

        botonAntiheroes = findViewById(R.id.btnAntiHeroes)
        botonAntiheroes.setOnClickListener{
            val intentAntiheroes = Intent(this,antiheroes::class.java)
            startActivity(intentAntiheroes)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menupelis, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_agregar){
            val intentListado = Intent(this, ListadoPeliculasActivity::class.java)
            startActivity(intentListado)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }



}
