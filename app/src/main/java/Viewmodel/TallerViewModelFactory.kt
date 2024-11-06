package com.example.motiva3.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuapp.motiva3.database.TallerRepository
import com.tuapp.motiva3.viewmodel.TallerViewModel

class TallerViewModelFactory(private val repository: TallerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TallerViewModel::class.java)) {
            return TallerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
