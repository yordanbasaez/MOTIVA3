package pages

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

import org.osmdroid.api.IMapController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.util.GeoPoint
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import kotlinx.coroutines.launch

@Composable
fun MapaView() {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    var direccion by remember { mutableStateOf(TextFieldValue("")) }
    var coordenadas by remember { mutableStateOf<GeoPoint?>(null) }
    val scope = rememberCoroutineScope() // El scope de la corrutina

    // Configurar la vista del mapa
    DisposableEffect(mapView) {
        mapView.setTileSource(TileSourceFactory.MAPNIK) // Configura la capa de OpenStreetMap
        mapView.setMultiTouchControls(true) // Habilita el control multitáctil
        mapView.controller.setZoom(10) // Configura el nivel de zoom inicial

        onDispose {
            mapView.onDetach()
        }
    }

    // Layout para la UI
    Column(modifier = Modifier.fillMaxSize()) {
        // Campo de texto para la dirección
        TextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Escribe una dirección") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        // Botón para buscar la dirección
        Button(
            onClick = {
                // Usar el scope para lanzar la corrutina y llamar la función suspendida
                scope.launch {
                    // Llamar la función para obtener las coordenadas desde la dirección
                    coordenadas = obtenerCoordenadas(direccion.text)
                    coordenadas?.let {
                        // Si las coordenadas son válidas, actualizar el mapa
                        val markerTaller = Marker(mapView)
                        markerTaller.position = it
                        markerTaller.title = "Ubicación de la dirección"
                        mapView.overlays.add(markerTaller)

                        // Mover el mapa al centro de la nueva ubicación
                        mapView.controller.setCenter(it)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text("Buscar Dirección")
        }

        // Mostrar el mapa
        AndroidView(factory = { mapView }) { mapView ->
            mapView.onResume()  // Asegúrate de reanudar el mapa cuando la vista sea visible
        }
    }
}


