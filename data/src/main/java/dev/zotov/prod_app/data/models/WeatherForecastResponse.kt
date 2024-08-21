package dev.zotov.prod_app.data.models

data class WeatherForecastResponse(
    val weather: List<WeatherResponse>,
    val main: WeatherMain,
)

data class WeatherResponse(
    val description: String,
    val icon: String,
)

data class WeatherMain(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
)