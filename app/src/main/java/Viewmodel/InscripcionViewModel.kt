package com.tuapp.motiva3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuapp.motiva3.modelo.Inscripciones
import com.tuapp.motiva33.database.InscripcionDao
import kotlinx.coroutines.launch

class InscripcionesViewModel(private val inscripcionesDao: InscripcionDao) : ViewModel() {

    fun agregarInscripcion(inscripcion: Inscripciones) {
        viewModelScope.launch {
            inscripcionesDao.agregarInscripcion(inscripcion)
        }
    }

    fun obtenerInscripcionesPorUsuario(idUsuario: String, onResult: (List<Inscripciones>) -> Unit) {
        viewModelScope.launch {
            val inscripciones = inscripcionesDao.obtenerInscripcionesPorUsuario(idUsuario)
            onResult(inscripciones)
        }
    }

    fun eliminarInscripcion(inscripcion: Inscripciones) {
        viewModelScope.launch {
            inscripcionesDao.eliminarInscripcion(inscripcion)
        }
    }
}
