package dev.zotov.prod_app

import android.content.SharedPreferences
import dev.zotov.prod_app.data.api.UserApi
import dev.zotov.prod_app.data.api.WeatherApi
import dev.zotov.prod_app.data.common.Api
import dev.zotov.prod_app.data.interfaces.TaskRepository
import dev.zotov.prod_app.data.interfaces.UsersRepository
import dev.zotov.prod_app.data.interfaces.VenuesRepository
import dev.zotov.prod_app.data.interfaces.WeatherRepository
import dev.zotov.prod_app.data.mappers.MapVenueMapperImpl
import dev.zotov.prod_app.data.mappers.RandomUserToUserMapperImpl
import dev.zotov.prod_app.data.mappers.RelatedVenueMapperImpl
import dev.zotov.prod_app.data.mappers.VenueMapperImpl
import dev.zotov.prod_app.data.mappers.VenuePhotoMapperImpl
import dev.zotov.prod_app.data.mappers.VenuesListMapperImpl
import dev.zotov.prod_app.data.mappers.WeatherForecastMapperImpl
import dev.zotov.prod_app.data.repositories.TaskRepositoryImpl
import dev.zotov.prod_app.data.repositories.UsersRepositoryImpl
import dev.zotov.prod_app.data.repositories.VenuesRepositoryImpl
import dev.zotov.prod_app.data.repositories.WeatherRepositoryImpl
import dev.zotov.prod_app.modules.home.HomeViewModel
import dev.zotov.prod_app.modules.map.MapViewModel
import dev.zotov.prod_app.modules.profile.ProfileViewModel
import dev.zotov.prod_app.modules.tasks.TasksViewModel

object DI {

    // Api
    private lateinit var weatherApi: WeatherApi
    private lateinit var userApi: UserApi

    // Repositories
    lateinit var venuesRepository: VenuesRepository
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var userRepositoryImpl: UsersRepository
    private lateinit var taskRepository: TaskRepository

    // View models
    lateinit var homeViewModel: HomeViewModel
    lateinit var profileViewModel: ProfileViewModel
    lateinit var tasksViewModel: TasksViewModel
    lateinit var mapViewModel: MapViewModel

    // Other
    lateinit var launchUri: (uri: String) -> Unit

    fun setup(
        userSharedPreferences: SharedPreferences,
        tasksSharedPreferences: SharedPreferences,
        launchUri: (String) -> Unit
    ) {
        // Api
        weatherApi = Api.weather
        userApi = Api.user

        // Repositories
        venuesRepository = VenuesRepositoryImpl(
            VenuesListMapperImpl,
            VenueMapperImpl,
            RelatedVenueMapperImpl,
            VenuePhotoMapperImpl,
            MapVenueMapperImpl,
        )
        weatherRepository = WeatherRepositoryImpl(
            WeatherForecastMapperImpl,
            weatherApi,
        )
        userRepositoryImpl = UsersRepositoryImpl(
            userSharedPreferences,
            userApi,
            RandomUserToUserMapperImpl,
        )
        taskRepository = TaskRepositoryImpl(
            tasksSharedPreferences,
        )

        // View Models
        homeViewModel = HomeViewModel(
            weatherRepository = weatherRepository,
            venuesRepository = venuesRepository,
        )
        profileViewModel = ProfileViewModel(
            usersRepository = userRepositoryImpl,
        )
        tasksViewModel = TasksViewModel(
            taskRepository = taskRepository,
            venueRepository = venuesRepository,
        )
        mapViewModel = MapViewModel(
            venuesRepository = venuesRepository,
        )

        // Other
        DI.launchUri = launchUri
    }
}