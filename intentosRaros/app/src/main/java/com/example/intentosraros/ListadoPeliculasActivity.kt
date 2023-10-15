package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.intentosraros.network.Serie
import com.example.intentosraros.network.SerieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListadoPeliculasActivity : AppCompatActivity() {
    lateinit var rvPeliculas : RecyclerView
    lateinit var peliculasAdapter: PeliculaAdapter
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_peliculas)

        rvPeliculas = findViewById(R.id.recyclerExamenes)
        peliculasAdapter = PeliculaAdapter(ArrayList(), this) // Inicializa el adaptador con una lista vacía
        lifecycleScope.launch {
            obtenerSeriesDesdeAPICorrutina()
        }
        rvPeliculas.adapter = peliculasAdapter

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo2)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menupj, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_personajes -> {
                val intentPj = Intent(this, PersonajesActivity::class.java)
                startActivity(intentPj)
                finish()
                return true
            }
            R.id.item_cerrar -> {
                // Código para cerrar sesión y redirigir al usuario a RegisterActivity
                val intentLogin = Intent(this, MainActivity::class.java)
                startActivity(intentLogin)
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
/*
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
    }*/

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




}