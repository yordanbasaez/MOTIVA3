package com.tuapp.motiva3.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Asegúrate de importar esto
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.tuapp.motiva3.viewmodel.InscripcionViewModel

@Composable
fun InscripcionScreen(
    inscripcionViewModel: InscripcionViewModel,
    usuarioId: String
) {
    // Llamar al ViewModel para obtener las inscripciones de un taller
    val inscripcionesState = inscripcionViewModel.obtenerInscripcionesPorTaller(tallerId = 1).collectAsState(initial = emptyList())

    // Mostrar la lista de inscripciones
    LazyColumn {
        items(inscripcionesState.value, key = { it.idInscripcion }) { inscripcion ->  // Utilizamos 'key' para optimizar la lista
            // Asegúrate de que idUsuario exista en la clase Inscripcion
            Text(text = "Usuario: ${inscripcion.idUsuario}")

            // Botón para inscribirse al taller
            Button(onClick = {
                // Lógica para inscribir al usuario al taller
                inscripcionViewModel.inscribirUsuarioEnTaller(tallerId = 1, usuarioId = usuarioId)
            }) {
                Text("Inscribirse")
            }
        }
    }
}



