package dev.zotov.prod_app.modules.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.zotov.prod_app.data.interfaces.FeedItem
import dev.zotov.prod_app.data.models.VenueListItem
import dev.zotov.prod_app.components.ServerErrorText
import dev.zotov.prod_app.components.UnableToLoadText
import dev.zotov.prod_app.modules.home.components.FeedItemSkeleton
import dev.zotov.prod_app.modules.home.components.HomeHeader
import dev.zotov.prod_app.modules.home.components.VenueCard
import dev.zotov.prod_app.modules.home.components.WeatherForecastCard
import dev.zotov.prod_app.modules.shared.LocationState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController
) {
    val weatherState by viewModel.weatherForecast.collectAsState()
    val locationState by viewModel.currentLocation.collectAsState()
    val feedState = viewModel.feed.collectAsState().value

    Scaffold(
        topBar = { HomeHeader(viewModel) },
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            item {
                Spacer(modifier = Modifier.padding(bottom = 20.dp))
            }

            if (locationState is LocationState.Unknown) {
                item { UnableToLoadText() }
            }

            if (weatherState is WeatherState.Error || feedState is FeedState.UnableToLoad) {
                item { ServerErrorText() }
            }

            when (weatherState) {
                is WeatherState.Idle, is WeatherState.Loading -> item {
                    WeatherForecastCard(
                        forecast = (weatherState as? WeatherState.Idle)?.forecast,
                        location = locationState,
                    )
                }
                else -> Unit
            }

            item {
                Spacer(modifier = Modifier.padding(bottom = 20.dp))
            }

            when (feedState) {
                is FeedState.Loading -> items(items = List(10) { it }) { FeedItemSkeleton() }
                is FeedState.Idle -> items(
                    items = feedState.feed,
                    key = { item: FeedItem -> item.id }
                ) { feedItem ->
                    when (feedItem) {
                        is VenueListItem -> VenueCard(
                            venue = feedItem,
                            onClick = {
                                navController.navigate("/venue/${feedItem.id}")
                            }
                        )
                    }
                    if (feedItem.id == feedState.feed.lastOrNull()?.id) {
                        viewModel.loadMoreFeed()
                    }
                }
                else -> Unit
            }
        }
    }
}