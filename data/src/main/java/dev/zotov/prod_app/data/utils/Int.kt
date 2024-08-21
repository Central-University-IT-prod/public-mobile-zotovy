package dev.zotov.prod_app.data.utils

import kotlin.math.pow

fun Int.roundToNearest(decimals: Int): Int {
    require(decimals >= 0) { "Decimals must be non-negative" }

    val multiplier = 10.0.pow(decimals)
    val roundedValue = (this / multiplier + 0.5).toInt() * multiplier
    return roundedValue.toInt()
}