package dev.zotov.prod_app.modules.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.zotov.prod_app.data.models.WeatherForecast
import dev.zotov.prod_app.modules.shared.LocationState

@Composable
fun WeatherForecastCard(forecast: WeatherForecast?, location: LocationState) {
    if (location is LocationState.Unknown) {
        return
    }

    if (forecast != null) {
        Box(modifier = containerModifier.testTag("weather-card")) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    WeatherIcon(forecast.type)

                    if (location.location != null) {
                        Text(
                            text = location.location!!,
                            style = TextStyle(
                                color = Color(0xFFFDFEFF).copy(alpha = 0.75f),
                                fontSize = 13.sp,
                            ),
                            modifier = Modifier.testTag("weather-location")
                        )
                    }
                }

                Column(modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.End) {
                    Text(
                        text = forecast.temperature,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.End,
                        ),
                        modifier = Modifier.testTag("weather-temp")
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = forecast.description,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = Modifier.testTag("weather-desc")
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    RichTextTemperature(
                        label = "",
                        value = forecast.tempSpread,
                        testTag = "weather-tempSpread",
                    )
                    RichTextTemperature(
                        label = "Ощущается как ",
                        value = forecast.feelsLikeTemp,
                        testTag = "weather-feelsLike",
                    )
                }
            }
        }
    } else {
        Skeleton()
    }
}

private val containerModifier = Modifier
    .fillMaxWidth()
    .background(
        brush = Brush.horizontalGradient(
            colors = listOf(Color(0xFF0BA5EC), Color(0xFF0086C9)),
        ),
        shape = RoundedCornerShape(16.dp)
    )
    .height(121.dp)
    .padding(10.dp)

@Composable
private fun RichTextTemperature(label: String, value: String, testTag: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 12.sp,
                    color = Color(0xFFFFFFFF).copy(alpha = 0.75f)
                )
            ) {
                append(label)
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 14.sp,
                    color = Color(0xFFFFFFFF)
                )
            ) {
                append(value)
            }
        },
        modifier = Modifier.testTag(testTag)
    )
}

@Composable
private fun Skeleton() {
    val modifier =
        Modifier.background(Color(0xFFFFFFFF).copy(alpha = 0.25f), shape = RoundedCornerShape(4.dp))

    Box(modifier = containerModifier.testTag("weather-skeleton")) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = modifier
                    .fillMaxHeight()
                    .width(121.dp)
            )

            Column(modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.End) {
                Box(modifier = modifier
                    .width(64.dp)
                    .height(20.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = modifier
                    .width(100.dp)
                    .height(18.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = modifier
                    .width(50.dp)
                    .height(14.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Box(modifier = modifier
                    .width(70.dp)
                    .height(14.dp))
            }
        }
    }
}