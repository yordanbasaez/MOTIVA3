package com.example.motiva33

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.motiva3.viewmodel.TallerViewModelFactory
import com.tuapp.motiva3.database.InscripcionRepository
import com.tuapp.motiva3.viewmodel.TallerViewModel
import com.tuapp.motiva3.viewmodel.UsuarioViewModel
import com.tuapp.motiva3.database.TallerDatabase
import com.tuapp.motiva3.database.TallerRepository
import com.tuapp.motiva3.navigation.NavGraph // Importa el NavGraph
import com.tuapp.motiva3.viewmodel.InscripcionViewModel
import com.tuapp.motiva3.viewmodel.InscripcionViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }

    @Composable
    fun MyApp() {
        // Configuración de la base de datos y los repositorios
        val db = TallerDatabase.getDatabase(applicationContext)

        // Repositorios y ViewModels
        val tallerRepository = TallerRepository(db.tallerDao())
        val tallerViewModel: TallerViewModel =
            viewModel(factory = TallerViewModelFactory(tallerRepository))

        // InscripcionViewModel
        val inscripcionRepository = InscripcionRepository(db.inscripcionDao())
        val inscripcionViewModel: InscripcionViewModel =
            viewModel(factory = InscripcionViewModelFactory(inscripcionRepository))

        // UsuarioViewModel
        val usuarioViewModel: UsuarioViewModel = viewModel()

        // Controlador de navegación
        val navController = rememberNavController()

        // Llamada al NavGraph
        NavGraph(
            navController = navController,
            viewModel = tallerViewModel,
            inscripcionViewModel = inscripcionViewModel,
            usuarioViewModel = usuarioViewModel
        )

        // Verifica si estás en la pantalla de login
        val currentScreen = navController.currentBackStackEntry?.destination?.route

        // Aquí añadimos un botón para abrir MapActivity
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Margen alrededor del contenido
        ) {
            // Solo muestra el botón si no estamos en la pantalla de login
            if (currentScreen != "LoginPage") {  // Ajusta "login_screen_route" al nombre real de la ruta de login
                // Botón en la parte superior derecha
                Box(
                    modifier = Modifier
                        .fillMaxWidth() // Ocupa todo el ancho disponible
                        .padding(top = 16.dp), // Espaciado desde la parte superior
                    contentAlignment = Alignment.TopEnd // Alineación en la parte superior derecha
                ) {
                    Button(onClick = {
                        // Crear el Intent para abrir MapActivity
                        val intent = Intent(this@MainActivity, MapActivity::class.java)
                        startActivity(intent)
                    }) {
                        Text(text = "Abrir Mapa")
                    }
                }
            }
        }
    }
}

