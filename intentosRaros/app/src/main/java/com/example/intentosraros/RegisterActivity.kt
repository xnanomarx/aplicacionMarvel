package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.example.intentosraros.UsuarioDao
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RegisterActivity : AppCompatActivity() {

    lateinit var TextoNombre: EditText
    lateinit var TextoApellido : EditText
    lateinit var TextoEmail: EditText
    lateinit var password: EditText
    lateinit var BotonRegistrar: Button
    lateinit var mensaje: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        TextoNombre = findViewById(R.id.TxtNombre)
        TextoApellido = findViewById(R.id.TxtApellido)
        password = findViewById(R.id.psswrd)
        TextoEmail = findViewById(R.id.TxtEmail)
        BotonRegistrar = findViewById(R.id.BtnRegistrar)

        BotonRegistrar.setOnClickListener {

            var nombre = TextoNombre.text.toString()
            var apellido = TextoApellido.text.toString()
            var email = TextoEmail.text.toString()
            var contrasenia = password.text.toString()
            if (TextoEmail.text.toString().isEmpty() || password.text.toString()
                    .isEmpty() || TextoNombre.text.toString()
                    .isEmpty() || TextoApellido.text.toString().isEmpty()
            ) {
                mensaje = "Faltan datos"
            } else {
                lifecycleScope.launch {
                    val emailNuevo = email
                    val bdd = AppDataBase.getDatabase(this@RegisterActivity)
                    val checkMail = bdd.usuarioDao().GetUserByUserName(emailNuevo)

                    if (checkMail == null) {
                        // El email no está registrado, procede con el registro del usuario
                        mensaje = "Usuario registrado con éxito"

                        var nuevoUsuario = Usuario(nombre, apellido, email, contrasenia)

                        withContext(Dispatchers.IO){//inserto al usuario utilizando un hilo de fondo
                            AppDataBase.getDatabase(this@RegisterActivity).usuarioDao().insertUsuario(nuevoUsuario)
                        }

                        val intentInicio2 = Intent(this@RegisterActivity, MarvelHistoriaActivity::class.java)
                        startActivity(intentInicio2)
                        finish()
                    } else {
                        // El email ya está registrado
                        mensaje = "Este email ya está en uso"
                    }
                }
            }

            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }

    }

}