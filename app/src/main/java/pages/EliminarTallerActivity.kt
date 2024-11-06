package com.tuapp.motiva3.pages

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.tuapp.motiva3.viewmodel.TallerViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.motiva33.ui.theme.Motiva3Theme
import com.example.motiva3.viewmodel.TallerViewModelFactory
import com.tuapp.motiva3.database.TallerDatabase
import com.tuapp.motiva3.database.TallerRepository

class EliminarTallerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val tallerId = intent.getIntExtra("TALLER_ID", -1) // Obtiene el ID del taller de los extras
            if (tallerId != -1) {
                // Inicializa el ViewModel
                val database = TallerDatabase.getDatabase(this)
                val tallerDao = database.tallerDao()
                val repository = TallerRepository(tallerDao)
                val viewModelFactory = TallerViewModelFactory(repository)
                val viewModel: TallerViewModel = viewModel(factory = viewModelFactory)

                // Llama a EliminarTallerScreen y pasa el viewModel y el tallerId
                EliminarTallerScreen(viewModel = viewModel, tallerId = tallerId)
            } else {
                // Maneja el caso en que no se pasa un ID válido
                Toast.makeText(this, "ID de taller no válido", Toast.LENGTH_SHORT).show()
                finish() // Cierra la actividad si no hay ID válido
            }
        }
    }
}




@Composable
fun EliminarTallerScreen(viewModel: TallerViewModel, tallerId: Int) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Eliminar Taller", style = MaterialTheme.typography.titleLarge)

        Text("¿Estás seguro de que deseas eliminar este taller?")

        Spacer(modifier = Modifier.height(16.dp)) // Espaciador para separar elementos

        Button(onClick = {
            viewModel.eliminarTaller(tallerId) // Asegúrate de que tallerId sea pasado correctamente
            Toast.makeText(context, "Taller eliminado exitosamente", Toast.LENGTH_SHORT).show()
            (context as? Activity)?.finish() // Cierra la actividad actual
        }, modifier = Modifier.align(Alignment.End)) {
            Text("Eliminar Taller")
        }

        Spacer(modifier = Modifier.height(8.dp)) // Espaciador para separar elementos

        Button(onClick = {
            (context as? Activity)?.finish() // Botón para cancelar y volver
        }, modifier = Modifier.align(Alignment.End)) {
            Text("Cancelar")
        }
    }
}

