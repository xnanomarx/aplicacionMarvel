package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class villanos : AppCompatActivity() {

    private lateinit var villano1: Button
    private lateinit var villano2: Button
    private lateinit var villano3: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_villanos)

        villano1 = findViewById(R.id.btnWanda)
        villano2 = findViewById(R.id.btnModok)
        villano3 = findViewById(R.id.btnDoom)

        villano1.setOnClickListener{
            val intentVillano1 = Intent(this, WandaActivity::class.java)
            startActivity(intentVillano1)
        }
        villano2.setOnClickListener{
            val intentVillano2 = Intent(this, ModokActivity::class.java)
            startActivity(intentVillano2)
        }
        villano3.setOnClickListener{
            val intentVillano3 = Intent(this, DoctorDoomActivity::class.java)
            startActivity(intentVillano3)
        }

    }
}