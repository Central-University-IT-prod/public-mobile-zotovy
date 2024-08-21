package dev.prod_app.modules.home.components

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import dev.zotov.prod_app.data.models.ForecastType
import dev.zotov.prod_app.data.models.WeatherForecast
import dev.zotov.prod_app.modules.home.components.WeatherForecastCard
import dev.zotov.prod_app.modules.shared.LocationState
import org.junit.Rule
import org.junit.Test

class WeatherForecastCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val forecast = WeatherForecast(
        temperature = "temp",
        feelsLikeTemp = "feels like",
        type = ForecastType.Snow,
        description = "description",
        tempSpread = "temp spread",
    )

    private val locationState = LocationState.Idle(
        locationName = "location name",
        lat = 0.0,
        lon = 0.0,
    )

    @Test
    fun testShouldRender() {
        composeTestRule.setContent {
            WeatherForecastCard(forecast = forecast, location = locationState)
        }

        composeTestRule.onNodeWithContentDescription("Snow").assertIsDisplayed()
        composeTestRule.onNodeWithTag("weather-location").assert(hasTextExactly("location name"))
        composeTestRule.onNodeWithTag("weather-temp").assert(hasTextExactly("temp"))
        composeTestRule.onNodeWithTag("weather-desc").assert(hasTextExactly("description"))
        composeTestRule.onNodeWithTag("weather-tempSpread").assert(hasTextExactly("temp spread"))
        composeTestRule.onNodeWithTag("weather-feelsLike").assert(hasTextExactly("Ощущается как feels like"))
    }

    @Test
    fun shouldRenderSkeleton() {
        composeTestRule.setContent {
            WeatherForecastCard(forecast = null, location = locationState)
        }

        composeTestRule.onNodeWithTag("weather-skeleton").assertIsDisplayed()
    }

    @Test
    fun shouldRenderNothing() {
        composeTestRule.setContent {
            WeatherForecastCard(forecast = null, location = LocationState.Unknown)
        }

        composeTestRule.onNodeWithTag("weather-skeleton").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("weather-card").assertIsNotDisplayed()
    }
}