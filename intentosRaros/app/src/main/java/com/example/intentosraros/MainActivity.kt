package com.example.intentosraros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
class MainActivity : AppCompatActivity() {

    lateinit var TextoEmail: EditText
    lateinit var password: EditText
    lateinit var recordar: CheckBox
    lateinit var BotonRegistrar: Button
    lateinit var BotonIniciarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TextoEmail = findViewById(R.id.TextoEmail)
        password = findViewById(R.id.password)
        recordar = findViewById(R.id.Recordar)
        BotonRegistrar = findViewById(R.id.BotonRegistrar)
        BotonIniciarSesion = findViewById(R.id.BotonIniciarSesion)

        var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales),
            MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario),"").toString()
        var passwordGuardada = preferencias.getString(resources.getString(R.string.password_usuario),"").toString()

        if(usuarioGuardado.isNotEmpty() && passwordGuardada.isNotEmpty()){
            startMainActivity(usuarioGuardado)
        }

        BotonRegistrar.setOnClickListener {
            val intentRegister = Intent(this,RegisterActivity::class.java)
            startActivity(intentRegister)
        }

        BotonIniciarSesion.setOnClickListener {
            var mensaje = "Iniciar sesion"
            var nom = TextoEmail.text.toString()
            var pas = password.text.toString()
            if(nom.isEmpty() || pas.isEmpty()){
                mensaje+= " - Faltan datos"
            }else{
                mensaje+= " - OK."
                if (recordar.isChecked && checkCredentials(nom,pas)){
                    var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales),
                        MODE_PRIVATE)
                    preferencias.edit().putString(resources.getString(R.string.nombre_usuario), nom).apply()
                    preferencias.edit().putString(resources.getString(R.string.password_usuario), pas).apply()
                    startMainActivity(TextoEmail.text.toString())
                }


                /*if(recordar.isChecked){
                    var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales),
                        MODE_PRIVATE)
                    preferencias.edit().putString(resources.getString(R.string.nombre_usuario), nom).apply()
                    preferencias.edit().putString(resources.getString(R.string.password_usuario), pas).apply()
                }*/
                if(checkCredentials(nom,pas)){
                    startMainActivity(TextoEmail.text.toString())
                }else{
                    Toast.makeText(this,"credenciales incorrectas", Toast.LENGTH_LONG).show()
                }



                //startMainActivity(TextoEmail.text.toString())
            }
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show()
        }


    }

    private fun startMainActivity(usuarioGuardado: String) {
        val intentInicio = Intent(this, MarvelHistoriaActivity::class.java)
        intentInicio.putExtra("nombre", usuarioGuardado)
        startActivity(intentInicio)
        finish()
    }

    fun checkCredentials(email:String,password:String):Boolean{
        val db = AppDataBase.getDatabase(this)
        val userDao = db.usuarioDao()
        val user = userDao.GetUserByUserName(email)

        return (user != null && user.password==password)
    }
}