package com.tuapp.motiva3.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.tuapp.motiva3.modelo.Taller
import com.tuapp.motiva3.viewmodel.TallerViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscarTallerScreen(navController: NavHostController, viewModel: TallerViewModel) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    var talleres by remember { mutableStateOf(emptyList<Taller>()) }

    // Usamos LaunchedEffect para observar cambios en la búsqueda
    LaunchedEffect(query.text) {
        viewModel.buscarTalleres(query.text).collectLatest { resultados ->
            talleres = resultados
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar Taller") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            TextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Buscar Taller") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(talleres) { taller ->
                    TallerItem(taller = taller, onInscribirClick = { selectedTaller ->
                        viewModel.inscribirTaller(selectedTaller)
                    })
                }
            }
        }
    }
}

@Composable
fun TallerItem(taller: Taller, onInscribirClick: (Taller) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    // Usamos Card para que cada taller tenga un estilo de cuadro atractivo
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(4.dp, shape = MaterialTheme.shapes.medium),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1)) // Fondo claro para destacar el contenido
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Nombre: ${taller.nombreTaller}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = "Descripción: ${taller.descripcion}")
            Text(text = "Precio: ${taller.precio}")
            Text(text = "Ubicación: ${taller.ubicacion}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onInscribirClick(taller) },
                modifier = Modifier.align(Alignment.End).padding(top = 4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("Inscribirse", color = Color.White)
            }
        }
    }
}
