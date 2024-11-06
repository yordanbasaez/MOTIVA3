package com.tuapp.motiva3.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Talleres")
data class Taller(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_Taller") val idTaller: Int = 0,
    @ColumnInfo(name = "NombreTaller") val nombreTaller: String,
    @ColumnInfo(name = "Descripcion") val descripcion: String,
    @ColumnInfo(name = "Precio") val precio: Double,
    @ColumnInfo(name = "Ubicacion") val ubicacion: String
)