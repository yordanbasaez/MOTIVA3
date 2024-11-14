import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.tuapp.motiva3.navigation.NavGraph
import com.tuapp.motiva3.viewmodel.InscripcionViewModel
import com.tuapp.motiva3.viewmodel.TallerViewModel
import com.tuapp.motiva3.viewmodel.UsuarioViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Inicialización de ViewModels
    val inscripcionViewModel: InscripcionViewModel = viewModel()  // O usa hiltViewModel() si usas Hilt
    val usuarioViewModel: UsuarioViewModel = viewModel() // Inicializa el UsuarioViewModel
    val tallerViewModel: TallerViewModel = viewModel() // Inicializa el TallerViewModel

    NavGraph(
        navController = navController,
        viewModel = tallerViewModel,
        usuarioViewModel = usuarioViewModel,
        inscripcionViewModel = inscripcionViewModel,  // Pasa el ViewModel aquí
        onInscripcionClick = {
            // Aquí puedes agregar cualquier lógica de manejo para las inscripciones
            navController.navigate("inscripcion")
        }
    )
}
