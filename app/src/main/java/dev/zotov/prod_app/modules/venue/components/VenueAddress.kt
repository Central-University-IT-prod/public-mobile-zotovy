package dev.zotov.prod_app.modules.venue.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.zotov.prod_app.data.models.VenueLocation
import dev.zotov.prod_app.R

@Composable
fun VenueAddress(location: VenueLocation, openMap: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 32.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.icon_map_pin),
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )

        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = "Адрес",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF344054),
                ),
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = location.formattedAddress,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF1D2939),
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            ClickableText(
                text = AnnotatedString("Открыть на карте"),
                onClick = { openMap() },
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = Color(0xFF3538CD),
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}