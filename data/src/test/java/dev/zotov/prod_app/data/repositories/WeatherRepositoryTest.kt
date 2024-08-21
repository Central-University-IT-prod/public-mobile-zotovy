package dev.zotov.prod_app.data.repositories

import android.util.Log
import dev.zotov.prod_app.data.api.WeatherApi
import dev.zotov.prod_app.data.interfaces.WeatherForecastMapper
import dev.zotov.prod_app.data.interfaces.WeatherRepository
import dev.zotov.prod_app.data.models.WeatherForecast
import dev.zotov.prod_app.data.models.WeatherForecastResponse
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.mockStatic
import retrofit2.Response

class WeatherRepositoryTest {

    private val mapper: WeatherForecastMapper = mock(WeatherForecastMapper::class.java)
    private val api: WeatherApi = mock(WeatherApi::class.java)

    private val repository: WeatherRepository = WeatherRepositoryImpl(
        mapper,
        api,
    )

    @Test
    fun `test successful forecast retrieval`() = runBlocking {
        val lat = 0.0
        val lon = 0.0

        val weatherForecastResponse = mock(WeatherForecastResponse::class.java)
        val responseBody = Response.success(weatherForecastResponse)
        val weatherForecast = mock(WeatherForecast::class.java)

        `when`(api.getCurrentWeather(lat, lon)).thenReturn(responseBody)
        `when`(mapper.map(weatherForecastResponse)).thenReturn(weatherForecast)

        val result = repository.getForecast(lat, lon)

        assertEquals(weatherForecast, result)
    }

    @Test
    fun `test unsuccessful forecast retrieval`() = runBlocking {
        val lat = 0.0
        val lon = 0.0
        val errorBody = mock(ResponseBody::class.java)
        val response = Response.error<WeatherForecastResponse>(400, errorBody)
        val logMock = mockStatic(Log::class.java)

        `when`(api.getCurrentWeather(lat, lon)).thenReturn(response)

        val result = repository.getForecast(lat, lon)

        assertEquals(null, result)
    }
}