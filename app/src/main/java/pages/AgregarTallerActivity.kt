    package com.tuapp.motiva3.pages

    import androidx.compose.foundation.layout.*
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.ArrowBack
    import androidx.compose.material3.*
    import androidx.compose.runtime.*
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.text.input.TextFieldValue
    import androidx.compose.ui.unit.dp
    import androidx.navigation.NavHostController
    import com.tuapp.motiva3.modelo.Taller
    import com.tuapp.motiva3.viewmodel.TallerViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AgregarTallerScreen(navController: NavHostController, viewModel: TallerViewModel) {
        var nombreTaller by remember { mutableStateOf(TextFieldValue("")) }
        var descripcion by remember { mutableStateOf(TextFieldValue("")) }
        var precio by remember { mutableStateOf(TextFieldValue("")) }
        var ubicacion by remember { mutableStateOf(TextFieldValue("")) }

        Scaffold(topBar = {
            TopAppBar(
                title = { Text("Agregar Taller") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                TextField(
                    value = nombreTaller,
                    onValueChange = { nombreTaller = it },
                    label = { Text("Nombre del Taller") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text("Precio") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = ubicacion,
                    onValueChange = { ubicacion = it },
                    label = { Text("Ubicación") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (nombreTaller.text.isNotBlank() && descripcion.text.isNotBlank() && precio.text.isNotBlank() && ubicacion.text.isNotBlank()) {
                            // Crea un objeto Taller y lo agrega a la base de datos
                            val nuevoTaller = Taller(
                                nombreTaller = nombreTaller.text,
                                descripcion = descripcion.text,
                                precio = precio.text.toDouble(),
                                ubicacion = ubicacion.text
                            )
                            viewModel.insertarTaller(nuevoTaller)
                            navController.popBackStack() // Regresar a la pantalla anterior
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Agregar Taller")
                }
            }
        }
    }
