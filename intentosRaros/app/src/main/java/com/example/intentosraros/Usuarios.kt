package com.example.intentosraros

import androidx.room.PrimaryKey
import androidx.room.Entity
import androidx.room.ColumnInfo

@Entity(tableName = "usuarios.table")
data class Usuario(
    @ColumnInfo(name = "nombre") val nombre:String,
    @ColumnInfo(name = "apellido")val apellido:String,
    @ColumnInfo(name = "email")val email:String,
    @ColumnInfo(name = "password")var password:String
){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}
