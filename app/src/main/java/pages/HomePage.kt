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
    modifier: Modifier = Modifier,
    viewModel: TallerViewModel,
    onAgregarTallerClick: () -> Unit,
    onBuscarTallerClick: () -> Unit
) {
    // Fondo degradado de negro a morado
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color.Black, Color(0xFF6200EE))
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(gradientBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mensaje de bienvenida
            Text(
                text = "Bienvenido a Motiva3",
                color = Color.White,
                fontSize = 32.sp,
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Imagen de logo en la parte superior
            Image(
                painter = painterResource(id = R.drawable.sportlogo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones para agregar y buscar taller
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botón de "Agregar Taller"
                // Botón de "Agregar Taller"
                Button(
                    onClick = onAgregarTallerClick,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBB86FC)) // Botón morado claro
                ) {
                    Text(
                        "Agregar Taller",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge // Usamos bodyLarge en lugar de body1
                    )
                }

                Button(
                    onClick = onBuscarTallerClick,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBB86FC)) // Botón morado claro
                ) {
                    Text(
                        "Buscar Taller",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge // Usamos bodyLarge en lugar de body1
                    )
                }

            }}}}