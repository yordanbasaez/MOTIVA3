package com.example.motiva33


import com.tuapp.motiva3.modelo.Inscripciones
import org.junit.Assert.assertEquals
import org.junit.Test

class InscripcionesTest {

    @Test
    fun `crear inscripcion y verificar datos`() {
        val inscripcion = Inscripciones(
            idUsuario = "12345",
            idTaller = 1
        )

        assertEquals("12345", inscripcion.idUsuario)
        assertEquals(1, inscripcion.idTaller)
    }
}