package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    lateinit var TextoNombre: EditText
    lateinit var TextoApellido : EditText
    lateinit var TextoEmail: EditText
    lateinit var password: EditText
    lateinit var BotonRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        TextoNombre = findViewById(R.id.TxtNombre)
        TextoApellido = findViewById(R.id.TxtApellido)
        password = findViewById(R.id.psswrd)
        TextoEmail = findViewById(R.id.TxtEmail)
        BotonRegistrar = findViewById(R.id.BtnRegistrar)

        BotonRegistrar.setOnClickListener {
            var mensaje = "Registro"
            if(TextoEmail.text.toString().isEmpty() || password.text.toString().isEmpty() || TextoNombre.text.toString().isEmpty() || TextoApellido.text.toString().isEmpty()){
                mensaje+= " - Faltan datos"
            }else{
                mensaje+= " - Usuario registrado"
                val intentInicio2 = Intent(this, PersonajesActivity::class.java)
                startActivity(intentInicio2)
                finish()
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }

    }
}