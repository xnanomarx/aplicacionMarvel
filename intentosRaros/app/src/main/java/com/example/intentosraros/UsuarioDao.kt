package com.example.intentosraros

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.selects.select

@Dao

interface UsuarioDao {
    @Query("select * from `usuarios.table`")
    fun getAll():List<Usuario>

    @Insert
    fun insertUsuario(usuario: Usuario)
}