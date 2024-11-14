package com.tuapp.motiva33.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tuapp.motiva3.modelo.Inscripciones
import kotlinx.coroutines.flow.Flow

@Dao
interface InscripcionDao {
    @Query("SELECT * FROM Inscripciones WHERE idTaller = :tallerId")
    fun getInscripcionesPorTaller(tallerId: Int): Flow<List<Inscripciones>>

    @Insert
    suspend fun insertarInscripcion(inscripcion: Inscripciones)
}