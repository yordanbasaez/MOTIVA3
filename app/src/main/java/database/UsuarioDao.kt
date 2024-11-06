package com.tuapp.motiva3.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tuapp.motiva3.Usuario

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insert(usuario: Usuario)

    @Query("SELECT * FROM usuarios") // Cambia "usuarios" por el nombre de la tabla en tu base de datos.
    suspend fun getAllUsuarios(): List<Usuario>

    // Agrega más métodos según sea necesario.
}