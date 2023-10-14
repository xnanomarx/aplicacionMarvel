package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
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

            var nombre = TextoNombre.text.toString()
            var apellido = TextoApellido.text.toString()
            var email = TextoEmail.text.toString()
            var contrasenia = password.text.toString()

            if(TextoEmail.text.toString().isEmpty() || password.text.toString().isEmpty() || TextoNombre.text.toString().isEmpty() || TextoApellido.text.toString().isEmpty()){
                mensaje+= " - Faltan datos"
            }else{
                mensaje+= " - Usuario registrado"

                var nuevoUsuario = Usuario(nombre, apellido, email, contrasenia)
                AppDataBase.getDatabase(this).usuarioDao().insertUsuario(nuevoUsuario)

                val intentInicio2 = Intent(this, MarvelHistoriaActivity::class.java)
                startActivity(intentInicio2)
                finish()
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }

    }
}