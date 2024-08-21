package dev.zotov.prod_app.data.mappers

import dev.zotov.prod_app.data.interfaces.WeatherForecastMapper
import dev.zotov.prod_app.data.models.ForecastType
import dev.zotov.prod_app.data.models.WeatherForecast
import dev.zotov.prod_app.data.models.WeatherForecastResponse
import java.util.Locale
import kotlin.math.roundToInt

object WeatherForecastMapperImpl : WeatherForecastMapper {
    override fun map(raw: WeatherForecastResponse): WeatherForecast {
        return WeatherForecast(
            temperature = "${raw.main.temp.roundToInt()}°",
            description = formatDescription(raw.weather.first().description),
            tempSpread = "от ${raw.main.temp_min.roundToInt()}° до ${raw.main.temp_max.roundToInt()}°",
            feelsLikeTemp = "${raw.main.feels_like.roundToInt()}°",
            type = mapIcon(raw.weather.first().icon)
        )
    }

    private fun mapIcon(type: String): ForecastType {
        if (type == "01d" || type == "01n") return ForecastType.ClearSky
        if (type == "02d" || type == "02n") return ForecastType.FewClouds
        if (type == "03d" || type == "03n") return ForecastType.ScatteredClouds
        if (type == "04d" || type == "04n") return ForecastType.BrokenClouds
        if (type == "09d" || type == "09n") return ForecastType.ShowerRain
        if (type == "10d" || type == "10n") return ForecastType.Rain
        if (type == "11d" || type == "11n") return ForecastType.Thunderstorm
        if (type == "13d" || type == "13n") return ForecastType.Snow
        if (type == "50d" || type == "50n") return ForecastType.Mist
        return ForecastType.ClearSky
    }

    private fun formatDescription(description: String): String {
        return description.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.ROOT)
            else it.toString()
        }
    }
}