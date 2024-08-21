package dev.zotov.prod_app.modules.map.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.MarkerState
import dev.zotov.prod_app.data.models.MapVenue

@Composable
fun VenueMarker(venue: MapVenue, onNavigate: () -> Unit) {
    var sheetOpen by remember { mutableStateOf(false) }

    AdvancedMarker(
        state = MarkerState(position = LatLng(venue.location.lat, venue.location.lon)),
        title = venue.name,
        onClick = {
            sheetOpen = true
            true
        }
    )

    if (sheetOpen) {
        VenueBottomSheet(
            venue = venue,
            onDismiss = { sheetOpen = false },
            onNavigate = onNavigate,
        )
    }
}