package dev.zotov.prod_app.data.repositories

import android.util.Log
import dev.zotov.prod_app.data.api.WeatherApi
import dev.zotov.prod_app.data.interfaces.WeatherForecastMapper
import dev.zotov.prod_app.data.interfaces.WeatherRepository
import dev.zotov.prod_app.data.models.WeatherForecast

class WeatherRepositoryImpl(
    private val weatherForecastMapper: WeatherForecastMapper,
    private val weatherApi: WeatherApi
) :
    WeatherRepository {

    override suspend fun getForecast(lat: Double, lon: Double): WeatherForecast? {
        val response = weatherApi.getCurrentWeather(lat, lon)

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return weatherForecastMapper.map(body)
            } else {
                Log.d(
                    "WeatherRepositoryImpl",
                    "Failed to get forecast, body is null"
                )
            }
        } else {
            Log.d(
                "WeatherRepositoryImpl",
                "Failed to get forecast, invalid api status ${response.code()}"
            )
        }

        return null
    }
}