package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class ListadoPeliculasActivity : AppCompatActivity() {
    lateinit var rvPeliculas : RecyclerView
    lateinit var peliculasAdapter: PeliculaAdapter
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_peliculas)

        rvPeliculas = findViewById(R.id.recyclerExamenes)
        peliculasAdapter = PeliculaAdapter(getPeliculas(), this)
        rvPeliculas.adapter = peliculasAdapter

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo2)
    }



    private fun getPeliculas(): MutableList<Pelicula> {
        var peliculas: MutableList<Pelicula> = ArrayList()
        peliculas.add(Pelicula(1, "Avengers 1", "2022-04-05"))
        peliculas.add(Pelicula(2, "Avengers 2", "2022-04-05"))
        peliculas.add(Pelicula(3, "Ingenieria de Software 1", "2022-04-05"))
        peliculas.add(Pelicula(4, "Ingenieria de Software 1", "2022-04-05"))
        peliculas.add(Pelicula(5, "Ingenieria de Software 1", "2022-04-05"))
        peliculas.add(Pelicula(6, "Ingenieria de Software 1", "2022-04-05"))
        peliculas.add(Pelicula(7, "Ingenieria de Software 1", "2022-04-05"))
        peliculas.add(Pelicula(8, "Ingenieria de Software 1", "2022-04-05"))
        peliculas.add(Pelicula(9, "Ingenieria de Software 1", "2022-04-05"))
        peliculas.add(Pelicula(10, "Ingenieria de Software 1", "2022-04-05"))
        peliculas.add(Pelicula(11, "Ingenieria de Software 1", "2022-04-05"))
        peliculas.add(Pelicula(12, "Ingenieria de Software 1", "2022-04-05"))
        peliculas.add(Pelicula(13, "Ingenieria de Software 1", "2022-04-05"))

        return peliculas
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_personajes){
            val intentPj = Intent(this, PersonajesActivity::class.java)
            startActivity(intentPj)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}