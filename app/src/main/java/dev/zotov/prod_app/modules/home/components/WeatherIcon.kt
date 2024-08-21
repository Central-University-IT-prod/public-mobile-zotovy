package dev.zotov.prod_app.modules.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.zotov.prod_app.data.models.ForecastType
import dev.zotov.prod_app.R
import java.util.Calendar

@Composable
fun WeatherIcon(type: ForecastType) {
    Image(
        painter = painterResource(id = getResId(type)),
        contentDescription = type.name,
        modifier = Modifier.height(73.dp).testTag("weather-icon"),
    )
}

private fun getResId(type: ForecastType): Int {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    if (currentHour in 6..19) return getDayResId(type)
    return getNightResId(type)
}

private fun getDayResId(type: ForecastType): Int {
    return when (type) {
        ForecastType.ClearSky -> R.drawable.icon_sun
        ForecastType.FewClouds -> R.drawable.icon_sun_clouds
        ForecastType.ScatteredClouds -> R.drawable.icon_cloud
        ForecastType.BrokenClouds -> R.drawable.icon_cloud
        ForecastType.ShowerRain -> R.drawable.icon_cloud_rain
        ForecastType.Rain -> R.drawable.icon_cloud_rain
        ForecastType.Thunderstorm -> R.drawable.icon_lightning
        ForecastType.Snow -> R.drawable.icon_snow
        ForecastType.Mist -> R.drawable.icon_sun_cloud_wind
    }
}

private fun getNightResId(type: ForecastType): Int {
    return when (type) {
        ForecastType.ClearSky -> R.drawable.icon_moon
        ForecastType.FewClouds -> R.drawable.icon_cloud
        ForecastType.ScatteredClouds -> R.drawable.icon_cloud
        ForecastType.BrokenClouds -> R.drawable.icon_cloud
        ForecastType.ShowerRain -> R.drawable.icon_cloud_rain
        ForecastType.Rain -> R.drawable.icon_cloud_rain
        ForecastType.Thunderstorm -> R.drawable.icon_lightning
        ForecastType.Snow -> R.drawable.icon_snow
        ForecastType.Mist -> R.drawable.icon_cloud
    }
}