package dev.zotov.prod_app.modules.venue.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.zotov.prod_app.data.models.RelatedVenue

@Composable
fun RelatedVenuesList(relatedVenues: List<RelatedVenue>, navController: NavController) {
    Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 28.dp)) {
        Text(
            text = "Похожие места",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF344054)
            ),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        for (venue in relatedVenues) {
            RelatedVenueCard(
                venue,
                onClick = { navController.navigate("/venue/${venue.id}") }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}