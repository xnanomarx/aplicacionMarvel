package com.example.intentosraros

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context

class MiDBHelper(context: Context) : SQLiteOpenHelper(context, "MiBaseDeDatos", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // Crea la tabla de usuarios si no existe
        db.execSQL("CREATE TABLE IF NOT EXISTS usuarios (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre_usuario TEXT, correo_electronico TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Aquí puedes realizar actualizaciones de la base de datos si es necesario
    }


    /*private fun correoElectronicoExiste(correoElectronico: String, usuarioDao: UsuarioDao): Boolean {
        val usuario = usuarioDao.GetUserByUserName(correoElectronico)
        return usuario != null
    }*/
    fun emailExists(email: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM 'usuarios.table' WHERE email = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(email))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }
}