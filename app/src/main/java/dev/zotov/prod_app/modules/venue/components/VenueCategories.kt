package dev.zotov.prod_app.modules.venue.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VenueCategories(categories: List<String>) {
    Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 36.dp)) {
        Text(
            text = "Категории",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF344054)
            ),
        )

        for (category in categories) {
            Text(
                text = category,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    color = Color(0xFF1D2939)
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}