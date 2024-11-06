package com.tuapp.motiva3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_Usuario") val idUsuario: Int = 0,
    @ColumnInfo(name = "Nombre") val nombre: String,
    @ColumnInfo(name = "Apellido") val apellido: String,
    @ColumnInfo(name = "Email") val email: String,
    @ColumnInfo(name = "Contraseña") val contraseña: String
)