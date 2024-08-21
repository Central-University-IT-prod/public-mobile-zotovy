package dev.zotov.prod_app.modules.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zotov.prod_app.data.interfaces.VenuesRepository
import dev.zotov.prod_app.data.models.MapVenue
import dev.zotov.prod_app.modules.shared.LocationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MapViewModel(private val venuesRepository: VenuesRepository) : ViewModel() {
    private var _location = MutableStateFlow<LocationState>(LocationState.Loading)
    val location: StateFlow<LocationState> get() = _location

    private var _venues = MutableStateFlow<List<MapVenue>>(emptyList())
    val venues: StateFlow<List<MapVenue>> get() = _venues

    fun setLocation(lat: Double, lon: Double) {
        _location.value = LocationState.Idle(
            lat = lat,
            lon = lon,
            locationName = null,
        )
        handleMapLocationChange(lat, lon)
    }

    fun setUnknownLocation() {
        _location.value = LocationState.Unknown
    }

    fun handleMapLocationChange(lat: Double, lon: Double) {
        viewModelScope.launch {
            _venues.value = venuesRepository.getNearbyVenues(lat, lon)
        }
    }
}
