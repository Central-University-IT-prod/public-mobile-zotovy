package dev.zotov.prod_app.data.interfaces

import dev.zotov.prod_app.data.models.WeatherForecast
import dev.zotov.prod_app.data.models.WeatherForecastResponse

interface WeatherForecastMapper {
    fun map(raw: WeatherForecastResponse): WeatherForecast
}