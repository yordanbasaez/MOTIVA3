package com.example.motiva33

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import pages.obtenerCoordenadas

class MapActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var editTextAddress: EditText
    private lateinit var btnSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Configura el user agent de osmdroid
        val appContext = applicationContext
        Configuration.getInstance().setUserAgentValue("com.example.motiva33/1.0")

        mapView = findViewById(R.id.mapview)
        editTextAddress = findViewById(R.id.editTextAddress)
        btnSearch = findViewById(R.id.btnSearch)

        // Configura la vista del mapa
        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)
        mapView.setBuiltInZoomControls(true)
        mapView.setMultiTouchControls(true)

        // Centra el mapa en una ubicación predeterminada (Ejemplo: Sydney)
        val mapController: IMapController = mapView.controller
        mapController.setZoom(10)
        mapController.setCenter(GeoPoint(-34.0, 151.0))

        // Escucha el evento de clic en el botón de búsqueda
        btnSearch.setOnClickListener {
            val address = editTextAddress.text.toString()
            if (address.isNotEmpty()) {
                // Llamar a la función suspend en una coroutine
                lifecycleScope.launch {
                    val geoPoint = obtenerCoordenadas(address)
                    if (geoPoint != null) {
                        // Centra el mapa en las nuevas coordenadas
                        mapController.setCenter(geoPoint)

                        // Elimina los marcadores previos
                        mapView.overlays.clear()

                        // Añadir un nuevo marcador en la ubicación obtenida
                        val marker = Marker(mapView)
                        marker.position = geoPoint
                        marker.title = "Ubicación: $address"
                        mapView.overlays.add(marker)

                        Toast.makeText(this@MapActivity, "Dirección encontrada", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MapActivity, "No se encontraron coordenadas para esa dirección", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Por favor ingresa una dirección", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
