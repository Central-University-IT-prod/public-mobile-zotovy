package dev.zotov.prod_app.data.interfaces

import dev.zotov.prod_app.data.models.WeatherForecast

/** Define an interface for the WeatherRepository to abstract the data layer. */
interface WeatherRepository {

    /**
     * Define a function to get the weather forecast for a given latitude and longitude.
     * @param lat The latitude of the location for which the forecast is requested.
     * @param lon The longitude of the location for which the forecast is requested.
     * @param callback The callback to handle the result of the forecast request.
     */
    suspend fun getForecast(lat: Double, lon: Double): WeatherForecast?
}