package com.example.motiva33

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.motiva3.viewmodel.TallerViewModelFactory
import com.tuapp.motiva3.database.TallerDao
import com.tuapp.motiva3.database.TallerDatabase
import com.tuapp.motiva3.database.TallerRepository
import com.tuapp.motiva3.pages.AgregarTallerScreen
import com.tuapp.motiva3.pages.BuscarTallerScreen // Asegúrate de importar la pantalla de búsqueda
import com.tuapp.motiva3.pages.HomePage
import com.tuapp.motiva3.viewmodel.TallerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Configuración de la base de datos y el ViewModel
            val db = TallerDatabase.getDatabase(applicationContext)
            val tallerDao: TallerDao = db.tallerDao()
            val repository = TallerRepository(tallerDao)
            val viewModelFactory = TallerViewModelFactory(repository)
            val viewModel: TallerViewModel = viewModel(factory = viewModelFactory)

            // Configuración de la navegación
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomePage(
                        viewModel = viewModel,
                        onAgregarTallerClick = {
                            navController.navigate("agregar_taller") // Navegar a la pantalla de agregar taller
                        },
                        onBuscarTallerClick = { // Agregar el parámetro de búsqueda
                            navController.navigate("buscar_taller") // Navegar a la pantalla de buscar taller
                        }
                    )
                }
                composable("agregar_taller") {
                    AgregarTallerScreen(navController, viewModel) // Asegúrate de pasar el viewModel también
                }
                composable("buscar_taller") {
                    BuscarTallerScreen(navController, viewModel) // Navegar a la pantalla de buscar taller
                }
            }
        }
    }
}
