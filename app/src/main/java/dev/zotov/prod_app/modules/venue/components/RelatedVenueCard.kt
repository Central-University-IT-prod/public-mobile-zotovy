package dev.zotov.prod_app.modules.venue.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.zotov.prod_app.data.models.RelatedVenue
import dev.zotov.prod_app.components.ScaleTap

@Composable
fun RelatedVenueCard(venue: RelatedVenue, onClick: () -> Unit) {
    ScaleTap(onClick = { onClick() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(1.dp, Color(0xFFEAEAEA), shape = RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text(
                text = venue.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1D2939),
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "${venue.category}, ${venue.location}",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF475467),
                ),
            )
        }
    }
}