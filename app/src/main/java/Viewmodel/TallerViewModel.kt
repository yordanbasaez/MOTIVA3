package com.tuapp.motiva3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuapp.motiva3.database.TallerRepository
import com.tuapp.motiva3.modelo.Taller
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
class TallerViewModel(private val repository: TallerRepository) : ViewModel() {
    // Flow que expone la lista de talleres desde el repositorio
    val talleres: StateFlow<List<Taller>> = repository.obtenerTalleres()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Función para agregar un taller nuevo
    fun insertarTaller(taller: Taller) {
        viewModelScope.launch {
            repository.insertarTaller(taller)
        }
    }

    // Función para editar un taller
    fun editarTaller(taller: Taller) {
        viewModelScope.launch {
            repository.insertarTaller(taller) // Reemplaza el taller existente
        }
    }

    // Función para eliminar un taller por su ID
    fun eliminarTaller(tallerId: Int) {
        viewModelScope.launch {
            repository.eliminarTallerPorId(tallerId)
        }
    }

    // Función para buscar talleres basados en la consulta
    fun buscarTalleres(query: String): Flow<List<Taller>> {
        return repository.buscarTalleres(query)
    }
}
