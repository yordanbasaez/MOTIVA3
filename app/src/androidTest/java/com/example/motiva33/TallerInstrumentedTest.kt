package com.example.motiva33

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.tuapp.motiva3.database.TallerDatabase
import com.tuapp.motiva3.modelo.Taller
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TallerInstrumentedTest {

    private lateinit var db: TallerDatabase

    @Before
    fun setUp() {
        // Configurar la base de datos
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = TallerDatabase.getDatabase(context)
    }

    @After
    fun tearDown() {
        // Cerrar la base de datos después de cada prueba
        db.close()
    }

    @Test
    fun crearYRecuperarTaller() = runBlocking {
        // Limpiar la tabla de talleres antes de la prueba
        db.tallerDao().deleteAll()  // Asegúrate de tener un método deleteAll() en tu DAO

        // Insertar un taller
        val taller = Taller(
            nombreTaller = "Taller de Desarrollo Android",
            descripcion = "Aprende a desarrollar aplicaciones Android",
            precio = 50.0,
            ubicacion = "Sala 101"
        )

        // Insertar el taller y obtener el ID generado
        val tallerId: Long = db.tallerDao().insertarTaller(taller)  // El ID se obtiene como Long

        // Log para verificar el ID insertado
        Log.d("TallerTest", "ID del taller insertado: $tallerId")

        // Crear una copia del taller con el ID asignado (convertir Long a Int)
        val tallerConId = taller.copy(idTaller = tallerId.toInt())  // Convertir el Long a Int

        // Recuperar todos los talleres usando el Flow y convertirlo a lista
        val talleres = db.tallerDao().getAllTalleres()
            .first()  // Usa first() para obtener la primera emisión del Flow

        // Log para verificar los talleres recuperados
        Log.d("TallerTest", "Talleres recuperados de la base de datos: $talleres")

        // Verificar que el taller insertado esté presente
        assertEquals(1, talleres.size)  // Ahora debería haber solo un taller
        assertEquals(tallerConId.idTaller, talleres[0].idTaller)
        assertEquals(tallerConId.nombreTaller, talleres[0].nombreTaller)
        assertEquals(tallerConId.descripcion, talleres[0].descripcion)
        assertEquals(tallerConId.precio, talleres[0].precio, 0.0)
        assertEquals(tallerConId.ubicacion, talleres[0].ubicacion)
    }}
