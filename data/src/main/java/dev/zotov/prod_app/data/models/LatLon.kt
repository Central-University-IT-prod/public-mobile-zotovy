package dev.zotov.prod_app.data.models

import kotlin.math.pow
import kotlin.math.sqrt

data class LatLon(
    val lat: Double,
    val lon: Double,
)

/// Returns distance between 2 points in km
fun LatLon.distance(point: LatLon): Double {
    return sqrt((point.lat - this.lat).pow(2) + (point.lon - this.lon).pow(2)) * 111
}

fun LatLon.toLL(): String = "$lat,$lon"