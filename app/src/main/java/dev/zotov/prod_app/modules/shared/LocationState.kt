package dev.zotov.prod_app.modules.shared

sealed class LocationState {
    data object Loading : LocationState()

    data object Unknown : LocationState()

    data class Idle(val lat: Double, val lon: Double, val locationName: String?) : LocationState()

    val location: String?
        get() = when (this) {
            is Idle -> this.locationName
            else -> null
        }
}