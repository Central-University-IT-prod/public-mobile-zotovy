package dev.zotov.prod_app.modules.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import dev.zotov.prod_app.R
import dev.zotov.prod_app.components.Loader
import dev.zotov.prod_app.components.UnableToLoadText
import dev.zotov.prod_app.modules.map.components.VenueMarker
import dev.zotov.prod_app.modules.shared.LocationState

@Composable
fun MapScreen(
    mapViewModel: MapViewModel,
    navController: NavController
) {

    when (val locationState = mapViewModel.location.collectAsState().value) {
        is LocationState.Idle -> MapContent(
            locationState,
            mapViewModel,
            navController
        )

        is LocationState.Unknown -> UnableToLoadText()
        is LocationState.Loading -> Loader()
    }
}

@Composable
private fun MapContent(
    location: LocationState.Idle,
    mapViewModel: MapViewModel,
    navController: NavController,
) {
    val venues = mapViewModel.venues.collectAsState().value

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(location.lat, location.lon), 15f)
    }

    LaunchedEffect(cameraPositionState.isMoving) {
        if (!cameraPositionState.isMoving) {
            val point = cameraPositionState.position.target
            mapViewModel.handleMapLocationChange(point.latitude, point.longitude)
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        googleMapOptionsFactory = {
            GoogleMapOptions().mapId("PROD_MAP")
        },
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
                LocalContext.current,
                R.raw.map_style
            )
        )
    ) {
        for (venue in venues) {
            VenueMarker(venue = venue) {
                navController.navigate("/venue/${venue.id}")
            }
        }
    }
}