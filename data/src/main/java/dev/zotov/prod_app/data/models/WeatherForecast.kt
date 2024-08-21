package dev.zotov.prod_app.data.models

data class WeatherForecast(
    val temperature: String,
    val description: String,
    val tempSpread: String,
    val feelsLikeTemp: String,
    val type: ForecastType,
)
