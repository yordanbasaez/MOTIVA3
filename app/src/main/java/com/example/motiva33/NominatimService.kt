package com.example.motiva33

 // O el paquete adecuado


import modelo.GeocodeResponse
import retrofit2.http.GET
import retrofit2.http.Query


// Interfaz para la API de Nominatim (OpenStreetMap)

interface NominatimService {
    @GET("search")
    suspend fun geocode(
        @Query("q") address: String,  // Dirección para buscar
        @Query("format") format: String = "json"  // Formato de respuesta (json)
    ): List<GeocodeResponse>
}