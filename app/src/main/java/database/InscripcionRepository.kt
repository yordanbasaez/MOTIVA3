package com.tuapp.motiva3.database

import com.tuapp.motiva3.modelo.Inscripciones
import com.tuapp.motiva33.database.InscripcionDao
import kotlinx.coroutines.flow.Flow

class InscripcionRepository(private val inscripcionDao: InscripcionDao) {

    // Método para insertar una inscripción
    suspend fun insertarInscripcion(inscripcion: Inscripciones) {
        inscripcionDao.insertarInscripcion(inscripcion)
    }

    // Obtener inscripciones por taller
    fun obtenerInscripcionesPorTaller(tallerId: Int): Flow<List<Inscripciones>> {
        return inscripcionDao.getInscripcionesPorTaller(tallerId)  // Debería devolver un Flow
    }
}

