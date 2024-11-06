package com.tuapp.motiva3.database

import com.tuapp.motiva3.modelo.Taller
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class TallerRepository(private val tallerDao: TallerDao) {
    suspend fun insertarTaller(taller: Taller) {
        tallerDao.insertarTaller(taller)
    }

    fun obtenerTalleres(): Flow<List<Taller>> {
        return tallerDao.obtenerTalleres()
    }

    suspend fun eliminarTallerPorId(tallerId: Int) {
        tallerDao.eliminarTallerPorId(tallerId)
    }

    fun buscarTalleres(query: String): Flow<List<Taller>> {
        return tallerDao.buscarTalleres(query)
    }
}