package dev.zotov.prod_app.data.common

import dev.zotov.prod_app.data.api.UserApi
import dev.zotov.prod_app.data.api.VenuesApi
import dev.zotov.prod_app.data.api.WeatherApi

object Api {
    val weather: WeatherApi
        get() = RetrofitClient.getClient("https://api.openweathermap.org/")
            .create(WeatherApi::class.java)

    val venues: VenuesApi
        get() = RetrofitClient.getClient("https://api.foursquare.com/")
            .create(VenuesApi::class.java)

    val user: UserApi
        get() = RetrofitClient.getClient("https://randomuser.me/")
            .create(UserApi::class.java)
}