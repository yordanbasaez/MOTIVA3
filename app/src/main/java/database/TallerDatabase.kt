package com.tuapp.motiva3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


import com.tuapp.motiva3.Usuario
import com.tuapp.motiva3.modelo.Taller


@Database(entities = [Usuario::class, Taller::class], version = 1)
abstract class TallerDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun tallerDao(): TallerDao

    companion object {
        @Volatile
        private var INSTANCE: TallerDatabase? = null

        fun getDatabase(context: Context): TallerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TallerDatabase::class.java,
                    "taller_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}