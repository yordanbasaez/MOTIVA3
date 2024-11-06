package com.tuapp.motiva3.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.tuapp.motiva3.modelo.Taller
import kotlinx.coroutines.flow.Flow

@Dao
interface TallerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTaller(taller: Taller)

    @Query("SELECT * FROM talleres")
    fun obtenerTalleres(): Flow<List<Taller>>

    @Query("DELETE FROM talleres WHERE ID_Taller = :tallerId")
    suspend fun eliminarTallerPorId(tallerId: Int)

    @Query("SELECT * FROM talleres WHERE nombreTaller LIKE '%' || :query || '%'")
    fun buscarTalleres(query: String): Flow<List<Taller>>
    @Query("SELECT * FROM talleres")
    fun obtenerTodosLosTalleres(): Flow<List<Taller>>
}

