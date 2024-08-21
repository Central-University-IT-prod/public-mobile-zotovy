package dev.zotov.prod_app.data.mappers

import dev.zotov.prod_app.data.models.ForecastType
import dev.zotov.prod_app.data.models.WeatherForecast
import dev.zotov.prod_app.data.models.WeatherForecastResponse
import dev.zotov.prod_app.data.models.WeatherMain
import dev.zotov.prod_app.data.models.WeatherResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherForecastMapperTest {

    @Test
    fun `test successful forecast map`() {
        val response = WeatherForecastResponse(
            weather = listOf(
                WeatherResponse(
                    description = "Облачно",
                    icon = "02n"
                )
            ),
            main = WeatherMain(
                temp = 20.23,
                feels_like = 21.12,
                temp_max = 1000.0,
                temp_min = -1923.723,
            )
        )
        val forecast = WeatherForecast(
            temperature = "20°",
            description = "Облачно",
            tempSpread = "от -1924° до 1000°",
            feelsLikeTemp = "21°",
            type = ForecastType.FewClouds,
        )


        val result = WeatherForecastMapperImpl.map(response)

        assertEquals(forecast, result)
    }
}