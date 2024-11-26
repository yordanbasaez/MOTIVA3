package com.tuapp.motiva33.database

import androidx.room.*
import com.tuapp.motiva3.modelo.Inscripciones

@Dao
interface InscripcionesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarInscripcion(inscripcion: Inscripciones)

    @Query("SELECT * FROM Inscripciones WHERE idUsuario = :idUsuario")
    suspend fun obtenerInscripcionesPorUsuario(idUsuario: String): List<Inscripciones>

    @Delete
    suspend fun eliminarInscripcion(inscripcion: Inscripciones)
}
