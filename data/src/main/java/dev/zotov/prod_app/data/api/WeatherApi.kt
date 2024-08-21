package dev.zotov.prod_app.data.api

import dev.zotov.prod_app.data.models.WeatherForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    /**
     * Fetch weather forecast based on [lat] and [lon]
     */
    @GET("/data/2.5/weather?appid=secret&lang=ru&units=metric")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Response<WeatherForecastResponse>
}