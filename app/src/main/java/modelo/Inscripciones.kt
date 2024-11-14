package com.tuapp.motiva3.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Inscripciones")
data class Inscripciones(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idInscripcion") val idInscripcion: Int = 0,
    @ColumnInfo(name = "idUsuario") val idUsuario: String, // Aquí es donde tienes el campo idUsuario
    @ColumnInfo(name = "idTaller") val idTaller: Int
)
