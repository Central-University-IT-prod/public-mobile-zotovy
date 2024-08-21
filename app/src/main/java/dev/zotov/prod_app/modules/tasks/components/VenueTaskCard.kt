package dev.zotov.prod_app.modules.tasks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import dev.zotov.prod_app.data.models.VenueTask
import dev.zotov.prod_app.R
import dev.zotov.prod_app.components.ScaleTap
import java.time.format.DateTimeFormatter

@Composable
fun VenueTaskCard(venueTask: VenueTask, onClick: () -> Unit) {
    ScaleTap(onClick = onClick) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .border(1.dp, Color(0xFFEAEAEA), shape = RoundedCornerShape(12.dp))
                .padding(7.dp)
                .height(94.dp),
        ) {
            VenueImage(url = venueTask.venue.bestPhoto)

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = venueTask.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color(0xFF1D2939)
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = venueTask.venue.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF475467),
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = venueTask.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
//                    text = venueTask.date.toString(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF667085),
                    ),
                )
            }
        }
    }
}

@Composable
private fun VenueImage(url: String?) {
    Image(
        painter = url.let {
            if (url != null) {
                rememberAsyncImagePainter(url)
            } else {
                painterResource(id = R.drawable.img_placeholder)
            }
        },
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(120.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(6.dp)),
    )
}