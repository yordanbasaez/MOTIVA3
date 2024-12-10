package com.example.motiva33

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.tuapp.motiva3.database.TallerDatabase
import com.tuapp.motiva3.modelo.Inscripciones
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InscripcionesInstrumentedTest {

    private lateinit var db: TallerDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = TallerDatabase.getDatabase(context)
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun guardarYRecuperarInscripcion() = runBlocking {
        // Limpiar las inscripciones previas antes de la prueba
        db.inscripcionDao().deleteAll() // Asegúrate de agregar este método en tu DAO

        // Crear una inscripción de ejemplo
        val inscripcion = Inscripciones(idUsuario = "12345", idTaller = 1)

        // Insertar inscripción en la base de datos
        db.inscripcionDao().insertarInscripcion(inscripcion)

        // Recuperar todas las inscripciones
        val inscripciones = db.inscripcionDao().getAll()

        // Verificar que se haya guardado y recuperado correctamente
        assertEquals(1, inscripciones.size) // Ahora debería haber solo una inscripción
        assertEquals("12345", inscripciones[0].idUsuario)
        assertEquals(1, inscripciones[0].idTaller)
    }
}