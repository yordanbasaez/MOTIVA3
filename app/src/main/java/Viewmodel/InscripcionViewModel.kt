package com.tuapp.motiva3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuapp.motiva3.database.InscripcionRepository
import com.tuapp.motiva3.modelo.Inscripciones
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class InscripcionViewModel(private val inscripcionRepository: InscripcionRepository) : ViewModel() {

    // Método para inscribir un usuario en un taller
    fun inscribirUsuarioEnTaller(tallerId: Int, usuarioId: String) {
        viewModelScope.launch {
            val inscripcion = Inscripciones(idTaller = tallerId, idUsuario = usuarioId)
            inscripcionRepository.insertarInscripcion(inscripcion)
        }
    }

    // Método para obtener las inscripciones de un taller (retorna un Flow de Inscripciones)
    fun obtenerInscripcionesPorTaller(tallerId: Int): Flow<List<Inscripciones>> {
        return inscripcionRepository.obtenerInscripcionesPorTaller(tallerId)
    }
}
