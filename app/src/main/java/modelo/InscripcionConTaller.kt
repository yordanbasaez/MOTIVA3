package com.tuapp.motiva3.modelo

import androidx.room.ColumnInfo

data class InscripcionConTaller(
    @ColumnInfo(name = "idInscripcion") val idInscripcion: Int,  // Corresponde a la columna de "Inscripciones"
    @ColumnInfo(name = "idUsuario") val idUsuario: String,  // Corresponde a la columna de "Inscripciones"
    @ColumnInfo(name = "idTaller") val idTaller: Int,  // Corresponde a la columna de "Inscripciones"
    @ColumnInfo(name = "NombreTaller") val nombreTaller: String,  // Corresponde a la columna de "Talleres"
    @ColumnInfo(name = "Ubicacion") val ubicacionTaller: String  // Corresponde a la columna de "Talleres"
)
