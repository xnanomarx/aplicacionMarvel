package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class antiheroes : AppCompatActivity() {

    private lateinit var antiH1: Button
    private lateinit var antiH2: Button
    private lateinit var antiH3: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antiheroes)

        antiH1 = findViewById(R.id.btnVenom)
        antiH2 = findViewById(R.id.btnDeadpool)
        antiH3 = findViewById(R.id.btnWolverine)

        antiH1.setOnClickListener{
            val intentH1 = Intent(this, VenomActivity::class.java)
            startActivity(intentH1)
        }
        antiH2.setOnClickListener{
            val intentH2 = Intent(this, DeadpoolActivity::class.java)
            startActivity(intentH2)
        }
        antiH3.setOnClickListener{
            val intentH3 = Intent(this, WolverineActivity::class.java)
            startActivity(intentH3)
        }

    }
}