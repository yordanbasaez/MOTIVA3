package com.example.motiva33

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
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

        // Aquí agregamos el InscripcionViewModel
        val inscripcionRepository = InscripcionRepository(db.inscripcionDao())
        val inscripcionViewModel: InscripcionViewModel =
            viewModel(factory = InscripcionViewModelFactory(inscripcionRepository))

        // ViewModel de Usuario, si es necesario
        val usuarioViewModel: UsuarioViewModel = viewModel()

        // Controlador de navegación
        val navController = rememberNavController()

        // Llamada al NavGraph, pasando todos los ViewModels necesarios
        NavGraph(
            navController = navController,
            viewModel = tallerViewModel,
            inscripcionViewModel = inscripcionViewModel,
            usuarioViewModel = usuarioViewModel,
            onInscripcionClick = {
                // Aquí puedes manejar la navegación a la pantalla de inscripciones si es necesario
                navController.navigate("inscripcion")
            }
        )
    }
}
