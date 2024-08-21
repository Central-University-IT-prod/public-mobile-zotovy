package dev.zotov.prod_app.modules.venue

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.zotov.prod_app.data.interfaces.VenuesRepository
import dev.zotov.prod_app.data.models.Venue
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VenueViewModel(
    private val venuesRepository: VenuesRepository,
    private val launchUri: (uri: String) -> Unit
) : ViewModel() {
    private var _state = MutableStateFlow<VenueState>(VenueState.Loading)
    val state: StateFlow<VenueState> get() = _state

    private var fetchVenueJob: Job? = null

    fun fetchVenue(id: String) {
        if (fetchVenueJob != null && fetchVenueJob?.isActive == true) return

        fetchVenueJob = viewModelScope.launch {
            val venue = venuesRepository.getById(id)

            Log.d("venue", venue.toString())
            if (venue == null) {
                _state.value = VenueState.Error
            } else {
                _state.value = VenueState.Idle(
                    venue = venue,
                )
            }
        }
    }

    fun handleNavigationBack() {
        fetchVenueJob?.cancel()
        viewModelScope.launch {
            delay(300)
            _state.value = VenueState.Loading
        }
    }

    fun openVenueOnMap() {
        val state = _state.value
        if (state is VenueState.Idle) {
            val lat = state.venue.location.lat
            val lon = state.venue.location.lon
            val name = state.venue.name

            val source = "geo:$lat,$lon?q=$name"
            launchUri(source)
        }
    }

    fun openPhoneCall() {
        val state = _state.value
        if (state is VenueState.Idle && state.venue.contacts?.phone != null) {
            val phone = state.venue.contacts?.phone!!
            launchUri("tel:$phone")
        }
    }

    fun openFacebookPage() {
        val state = _state.value
        if (state is VenueState.Idle && state.venue.contacts?.facebook != null) {
            val facebook = state.venue.contacts?.facebook!!
            launchUri("https://www.facebook.com/$facebook")
        }
    }

    fun openTwitterPage() {
        val state = _state.value
        if (state is VenueState.Idle && state.venue.contacts?.twitter != null) {
            val twitter = state.venue.contacts?.twitter!!
            launchUri("https://twitter.com/#!/$twitter")
        }
    }

    fun openInstagramPage() {
        val state = _state.value
        if (state is VenueState.Idle && state.venue.contacts?.instagram != null) {
            val instagram = state.venue.contacts?.instagram!!
            launchUri("http://instagram.com/$instagram")
        }
    }
}

sealed class VenueState {
    data object Loading : VenueState()

    data class Idle(
        val venue: Venue,
    ) : VenueState()

    data object Error : VenueState()
}

class VenueViewModelFactory(
    private val venuesRepository: VenuesRepository,
    private val launchUri: (uri: String) -> Unit
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VenueViewModel::class.java)) {
            return VenueViewModel(venuesRepository, launchUri) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

