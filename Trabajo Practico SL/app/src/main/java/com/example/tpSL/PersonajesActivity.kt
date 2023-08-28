package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PersonajesActivity : AppCompatActivity() {

    lateinit var botonHeroes: Button
    lateinit var botonVillanos: Button
    lateinit var botonAntiheroes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personajes)

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
}