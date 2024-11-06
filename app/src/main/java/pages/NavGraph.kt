package com.tuapp.motiva3.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tuapp.motiva3.pages.AgregarTallerScreen
import com.tuapp.motiva3.pages.BuscarTallerScreen
import com.tuapp.motiva3.pages.HomePage
import com.tuapp.motiva3.viewmodel.TallerViewModel
@Composable
fun NavigationGraph(navController: NavHostController, viewModel: TallerViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomePage(
                viewModel = viewModel,
                onAgregarTallerClick = {
                    navController.navigate("agregar_taller")
                },
                onBuscarTallerClick = {
                    navController.navigate("buscar_taller") // Navegar a la pantalla de buscar taller
                }
            )
        }
        composable("agregar_taller") {
            AgregarTallerScreen(navController, viewModel)
        }
        composable("buscar_taller") {
            BuscarTallerScreen(navController, viewModel) // Agregar pantalla de búsqueda
        }
    }
}
