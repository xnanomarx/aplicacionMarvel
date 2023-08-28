package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class heroes : AppCompatActivity() {

    private lateinit var heroe1: Button
    private lateinit var heroe2: Button
    private lateinit var heroe3: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)

        heroe1 = findViewById(R.id.btnCapitana)
        heroe2 = findViewById(R.id.btnStrange)
        heroe3 = findViewById(R.id.btnSpiderman)

        heroe1.setOnClickListener{
            val intentHeroe1 = Intent(this, CapitanaMarvelActivity::class.java)
            startActivity(intentHeroe1)
        }
        heroe2.setOnClickListener{
            val intentHeroe2 = Intent(this, DrStrangeActivity::class.java)
            startActivity(intentHeroe2)
        }
        heroe3.setOnClickListener{
            val intentHeroe3 = Intent(this, SpidermanActivity::class.java)
            startActivity(intentHeroe3)
        }

    }
}