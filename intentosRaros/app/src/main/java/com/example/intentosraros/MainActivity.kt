package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var TextoEmail: EditText
    lateinit var password: EditText
    lateinit var Recordar: CheckBox
    lateinit var BotonRegistrar: Button
    lateinit var BotonIniciarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TextoEmail = findViewById(R.id.TextoEmail)
        password = findViewById(R.id.password)
        Recordar = findViewById(R.id.Recordar)
        BotonRegistrar = findViewById(R.id.BotonRegistrar)
        BotonIniciarSesion = findViewById(R.id.BotonIniciarSesion)

        BotonIniciarSesion.setOnClickListener {
            var mensaje = "Iniciar sesion"
            if(TextoEmail.text.toString().isEmpty() || password.text.toString().isEmpty()){
                mensaje+= " - Faltan datos"
            }else{
                mensaje+= " - OK."
                val intentInicio = Intent(this, PersonajesActivity::class.java)
                startActivity(intentInicio)


            }
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show()
        }
        BotonRegistrar.setOnClickListener {
            val intentRegister = Intent(this,RegisterActivity::class.java)
            startActivity(intentRegister)
        }


    }
}