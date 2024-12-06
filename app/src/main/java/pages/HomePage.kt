package com.tuapp.motiva3.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
    onInscripcionClick: () -> Unit
) {

    val buttonColor = Color(0xFF009688)
    val buttonTextColor = Color.White
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF009688), Color(0xFFD0D0D0))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Título llamativo
            Text(
                text = "¡Bienvenido a Motiva3!",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 32.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(bottom = 80.dp) // Espaciado aumentado debajo del título
            )

            // Botones
            Button(
                onClick = onAgregarTallerClick,
                colors = ButtonDefaults.buttonColors(buttonColor),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(10.dp, RoundedCornerShape(12.dp))
            ) {
                Text("Agregar Taller", color = buttonTextColor)
            }

            Button(
                onClick = onBuscarTallerClick,
                colors = ButtonDefaults.buttonColors(buttonColor),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(10.dp, RoundedCornerShape(12.dp))
            ) {
                Text("Buscar Taller", color = buttonTextColor)
            }

            Button(
                onClick = { onInscripcionClick() }, // Cambia según la nueva lógica
                colors = ButtonDefaults.buttonColors(buttonColor),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(10.dp, RoundedCornerShape(12.dp))
            ) {
                Text("Ver Inscripciones", color = buttonTextColor)
            }

        }
    }
}
