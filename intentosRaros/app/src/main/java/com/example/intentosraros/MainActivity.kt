package com.example.intentosraros

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import androidx.lifecycle.ViewModelProvider
class MainActivity : AppCompatActivity() {

    lateinit var TextoEmail: EditText
    lateinit var password: EditText
    lateinit var recordar: CheckBox
    lateinit var BotonRegistrar: Button
    lateinit var BotonIniciarSesion: Button

    val IDCanal = "chat"
    val NombreCanal="chat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TextoEmail = findViewById(R.id.TextoEmail)
        password = findViewById(R.id.password)
        recordar = findViewById(R.id.Recordar)
        BotonRegistrar = findViewById(R.id.BotonRegistrar)
        BotonIniciarSesion = findViewById(R.id.BotonIniciarSesion)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importancia = NotificationManager.IMPORTANCE_HIGH
            val Canal = NotificationChannel(IDCanal,NombreCanal,importancia)

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(Canal)

        }

        var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales),
            MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario),"").toString()
        var passwordGuardada = preferencias.getString(resources.getString(R.string.password_usuario),"").toString()

        if(usuarioGuardado.isNotEmpty() && passwordGuardada.isNotEmpty()){
            val notificacion = NotificationCompat.Builder(this, IDCanal).also {noti->
                noti.setContentTitle("Recordar Usuario")
                noti.setContentText("Usuario recordado correctamente")
                noti.setSmallIcon(R.drawable.baseline_man_24)
            }.build()

            val notificacionManager=NotificationManagerCompat.from(applicationContext)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notificacionManager.notify(1,notificacion)
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
                    mensaje = "Credenciales incorrectas"
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