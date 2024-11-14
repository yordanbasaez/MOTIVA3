package com.tuapp.motiva3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tuapp.motiva3.Usuario
import com.tuapp.motiva3.modelo.Inscripciones
import com.tuapp.motiva3.modelo.Taller
import com.tuapp.motiva33.database.InscripcionDao

@Database(entities = [Usuario::class, Taller::class, Inscripciones::class], version = 2)  // Actualiza la versión aquí
abstract class TallerDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun tallerDao(): TallerDao
    abstract fun inscripcionDao(): InscripcionDao  // DAO para Inscripciones

    companion object {
        @Volatile
        private var INSTANCE: TallerDatabase? = null

        fun getDatabase(context: Context): TallerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TallerDatabase::class.java,
                    "taller_database"
                )
                    .fallbackToDestructiveMigration()  // Esto elimina la base de datos si cambia la versión
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

