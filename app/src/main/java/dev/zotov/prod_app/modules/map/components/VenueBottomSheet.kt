package dev.zotov.prod_app.modules.map.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.zotov.prod_app.data.models.MapVenue
import dev.zotov.prod_app.components.Button

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VenueBottomSheet(venue: MapVenue, onDismiss: () -> Unit, onNavigate: () -> Unit) {
    ModalBottomSheet(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = venue.name,
                style = TextStyle(
                    color = Color(0xFF344054),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            if (venue.category != null) {
                Text(
                    text = venue.category!!,
                    style = TextStyle(
                        color = Color(0xFF667085),
                        fontSize = 18.sp,
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Button(text = "Подробнее", onClick = { onNavigate() })
        }
    }
}