package dev.zotov.prod_app.data.models

/**
 * Enum representing different types of weather forecasts.
 */
enum class ForecastType {
    /**
     * Represents a weather condition with no clouds in the sky and clear visibility.
     */
    ClearSky,

    /**
     * Represents a weather condition with a small number of clouds in the sky.
     */
    FewClouds,

    /**
     * Represents a weather condition with clouds scattered across the sky but not covering the entire sky.
     */
    ScatteredClouds,

    /**
     * Represents a weather condition with clouds covering a significant portion of the sky, but with visible gaps.
     */
    BrokenClouds,

    /**
     * Represents a weather condition with short periods of rain showers.
     */
    ShowerRain,

    /**
     * Represents a weather condition with continuous or prolonged rainfall.
     */
    Rain,

    /**
     * Represents a weather condition with thunderstorms, characterized by thunder, lightning, and heavy rainfall.
     */
    Thunderstorm,

    /**
     * Represents a weather condition with falling snow, leading to snow accumulation.
     */
    Snow,

    /**
     * Represents a weather condition with low-lying clouds near the ground, reducing visibility.
     */
    Mist
}
