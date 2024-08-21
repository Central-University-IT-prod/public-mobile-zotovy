package dev.zotov.prod_app.modules.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zotov.prod_app.data.interfaces.FeedItem
import dev.zotov.prod_app.data.interfaces.VenuesRepository
import dev.zotov.prod_app.data.interfaces.WeatherRepository
import dev.zotov.prod_app.data.models.WeatherForecast
import dev.zotov.prod_app.modules.shared.LocationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val weatherRepository: WeatherRepository,
    private val venuesRepository: VenuesRepository
) : ViewModel() {
    private var _weatherForecast = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherForecast: StateFlow<WeatherState> get() = _weatherForecast

    private var _currentLocation = MutableStateFlow<LocationState>(LocationState.Loading)
    val currentLocation: StateFlow<LocationState> get() = _currentLocation

    private var _feed = MutableStateFlow<FeedState>(FeedState.Loading)
    val feed: StateFlow<FeedState> get() = _feed

    private var loadingMoreFeed = false
    private var canLoadMoreFeed = true

    fun handleLocationChange(lat: Double, lon: Double, locationName: String?) {
        Log.d("handleLocationChange", "$lat $lon $locationName")
        _currentLocation.value = LocationState.Idle(lat, lon, locationName)
        fetchWeather()
        fetchVenues()
    }

    fun handleUnknownLocation() {
        Log.d("unknownLocation", "")
        _currentLocation.value = LocationState.Unknown
        _feed.value = FeedState.NoLocationData
    }

    private fun fetchWeather() {
        val location = currentLocation.value
        if (location is LocationState.Idle) {
            viewModelScope.launch {
                try {
                    val forecast = weatherRepository.getForecast(location.lat, location.lon)
                    _weatherForecast.value = if (forecast != null) WeatherState.Idle(forecast) else WeatherState.Error
                } catch (e: Throwable) {
                    _weatherForecast.value = WeatherState.Error
                }
            }
        }
    }

    private fun fetchVenues() {
        val location = currentLocation.value
        if (location is LocationState.Idle) {
            viewModelScope.launch {
                val recommendations =
                    venuesRepository.fetchRecommendations(location.lat, location.lon)
                _feed.value = FeedState.Idle(feed = recommendations)
            }
        }
    }

    fun loadMoreFeed() {
        if (loadingMoreFeed || !canLoadMoreFeed) return
        loadingMoreFeed = true

        val location = currentLocation.value
        val currentFeed = feed.value

        if (location is LocationState.Idle && currentFeed is FeedState.Idle) {
            viewModelScope.launch {
                val recommendations = venuesRepository.fetchRecommendations(
                    location.lat,
                    location.lon,
                    offset = currentFeed.feed.size
                )
                _feed.value = FeedState.Idle(feed = currentFeed.feed.plus(recommendations))
                loadingMoreFeed = false
                canLoadMoreFeed = recommendations.isNotEmpty()
            }
        }
    }
}

sealed class WeatherState {
    data object Loading : WeatherState()

    data class Idle(val forecast: WeatherForecast) : WeatherState()

    data object Error : WeatherState()
}

sealed class FeedState {
    data object Loading : FeedState()

    data class Idle(
        val feed: List<FeedItem>,
    ) : FeedState()

    data object UnableToLoad : FeedState()

    data object NoLocationData: FeedState()
}
