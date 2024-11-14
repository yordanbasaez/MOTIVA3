package com.tuapp.motiva3.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tuapp.motiva3.pages.AgregarTallerScreen
import com.tuapp.motiva3.pages.BuscarTallerScreen
import com.tuapp.motiva3.pages.HomePage
import com.tuapp.motiva3.pages.InscripcionScreen
import com.tuapp.motiva3.viewmodel.InscripcionViewModel
import com.tuapp.motiva3.viewmodel.TallerViewModel
import com.tuapp.motiva3.viewmodel.UsuarioViewModel
import pages.LoginPage
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: TallerViewModel,
    usuarioViewModel: UsuarioViewModel,
    inscripcionViewModel: InscripcionViewModel,
    onInscripcionClick: () -> Unit // Manejo del clic para inscripciones
) {
    // Define el NavHost que maneja la navegación
    NavHost(navController = navController, startDestination = "login") {
        // Pantalla de Login
        composable("login") {
            LoginPage(
                viewModel = usuarioViewModel,
                onLoginSuccess = {

                    if (navController.currentBackStackEntry?.destination?.route != "home") {
                        navController.navigate("home") {
                            // Limpiar el back stack para que no se pueda regresar al login
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }
            )
        }

        // Pantalla de Home
        composable("home") {
            HomePage(
                viewModel = viewModel,
                onAgregarTallerClick = {
                    navController.navigate("agregar_taller") // Navegar a agregar taller
                },
                onBuscarTallerClick = {
                    navController.navigate("buscar_taller") // Navegar a buscar taller
                },
                onInscripcionClick = onInscripcionClick // Se pasa el parámetro aquí
            )
        }

        // Pantalla para agregar un taller
        composable("agregar_taller") {
            AgregarTallerScreen(navController, viewModel)
        }

        // Pantalla para buscar un taller
        composable("buscar_taller") {
            BuscarTallerScreen(navController, viewModel)
        }

        // Pantalla para inscripciones
        composable("inscripcion") {
            InscripcionScreen(
                inscripcionViewModel = inscripcionViewModel,  // Aquí pasa el InscripcionViewModel
                usuarioId = "user_id_here" // O un ID real para el usuario
            )
        }
    }
}


