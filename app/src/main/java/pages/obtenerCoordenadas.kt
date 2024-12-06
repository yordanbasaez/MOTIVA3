package pages
import android.util.Log
import com.example.motiva33.NominatimService
import org.osmdroid.util.GeoPoint
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader

suspend fun obtenerCoordenadas(address: String): GeoPoint? {
    try {
        // Crear una instancia de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://nominatim.openstreetmap.org/")  // URL base de Nominatim
            .addConverterFactory(GsonConverterFactory.create())  // Convertir respuesta JSON a objetos Kotlin
            .build()

        // Crear una instancia de la interfaz NominatimService
        val service = retrofit.create(NominatimService::class.java)

        // Hacer la solicitud para obtener las coordenadas
        val response = service.geocode(address)

        if (response.isNotEmpty()) {
            // Si la respuesta tiene resultados, tomar el primero y convertirlo a coordenadas
            val lat = response[0].lat.toDouble()
            val lon = response[0].lon.toDouble()
            return GeoPoint(lat, lon)
        } else {
            // Si no se encuentra la dirección, mostrar mensaje
            Log.d("MapaView", "No se encontraron coordenadas para la dirección.")
        }
    } catch (e: Exception) {
        // Manejo de excepciones en caso de error
        e.printStackTrace()
    }
    return null
}

