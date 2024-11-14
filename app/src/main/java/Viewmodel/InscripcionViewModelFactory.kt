package com.tuapp.motiva3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuapp.motiva3.database.InscripcionRepository

class InscripcionViewModelFactory(private val inscripcionRepository: InscripcionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InscripcionViewModel::class.java)) {
            return InscripcionViewModel(inscripcionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
