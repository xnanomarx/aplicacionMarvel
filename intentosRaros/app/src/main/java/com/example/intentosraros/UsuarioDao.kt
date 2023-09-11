package com.example.intentosraros

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.selects.select

@Dao

interface UsuarioDao {
    @Query("select * from `usuarios.table`")
    fun getAll():List<Usuario>

    @Query("select * from `usuarios.table` where email = :email")
    fun GetUserByUserName(email:String): Usuario?


    @Query("SELECT * FROM 'usuarios.table' WHERE email = :correoElectronico")
    fun emailExists (correoElectronico: String): Usuario?

    @Insert
    fun insertUsuario(usuario: Usuario)
}