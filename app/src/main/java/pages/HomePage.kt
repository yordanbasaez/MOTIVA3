package com.tuapp.motiva3.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.motiva33.R
import com.tuapp.motiva3.viewmodel.TallerViewModel
@Composable
fun HomePage(
    viewModel: TallerViewModel,
    onAgregarTallerClick: () -> Unit,
    onBuscarTallerClick: () -> Unit,
    onInscripcionClick: () -> Unit // Asegúrate de que este parámetro esté presente
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(onClick = onAgregarTallerClick) {
            Text("Agregar Taller")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBuscarTallerClick) {
            Text("Buscar Taller")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para navegar a la pantalla de inscripción
        Button(onClick = onInscripcionClick) {
            Text("Ver Inscripciones")
        }
    }
}
