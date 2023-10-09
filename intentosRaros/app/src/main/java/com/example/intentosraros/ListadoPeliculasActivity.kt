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
        menuInflater.inflate(R.menu.menupj, menu)
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
<<<<<<< Updated upstream
=======

    private fun obtenerSeriesDesdeAPI() {
        val client = ApiClient.apiService.fetchSeries(20) // Cambia el límite según tus necesidades

        client.enqueue(object : Callback<SerieResponse> {
            override fun onResponse(call: Call<SerieResponse>, response: Response<SerieResponse>) {
                if (response.isSuccessful) {
                    val seriesResponse = response.body()
                    if (seriesResponse != null) {
                        val series = seriesResponse.data.results
                        actualizarRecyclerView(series)
                    } else {
                        Toast.makeText(this@ListadoPeliculasActivity, "Respuesta vacía", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Maneja el caso en el que la respuesta no es exitosa
                    Toast.makeText(this@ListadoPeliculasActivity, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SerieResponse>, t: Throwable) {
                // Maneja el caso en el que haya un error de red
                Toast.makeText(this@ListadoPeliculasActivity, "No se pudo conectar", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private suspend fun obtenerSeriesDesdeAPICorrutina() {
        try {
            val series = withContext(Dispatchers.IO) {
                val response = ApiClient.apiService.fetchSeries(20).execute()
                if (response.isSuccessful) {
                    response.body()?.data?.results ?: emptyList()
                } else {
                    emptyList()
                }
            }

            // Actualizamos el RecyclerView en el hilo principal
            withContext(Dispatchers.Main) {
                actualizarRecyclerView(series)
            }
        } catch (e: Exception) {
            // Manejamos errores de red u otras excepciones en el hilo principal
            withContext(Dispatchers.Main) {
                Toast.makeText(this@ListadoPeliculasActivity, "Error en la conexion", Toast.LENGTH_SHORT).show()
                Log.e("API", "Error: ${e.message}", e)
            }
        }
    }

    private fun actualizarRecyclerView(series: List<Serie>) {
        peliculasAdapter.peliculas.clear() // Limpia los datos existentes en el adaptador
        peliculasAdapter.peliculas.addAll(series) // Agrega las nuevas series al adaptador
        peliculasAdapter.notifyDataSetChanged() // Notifica al adaptador que los datos han cambiado
    }



>>>>>>> Stashed changes

}