package modelo


// Respuesta de la API Nominatim (OpenStreetMap)
data class GeocodeResponse(
    val lat: String,  // Latitud
    val lon: String   // Longitud
)
