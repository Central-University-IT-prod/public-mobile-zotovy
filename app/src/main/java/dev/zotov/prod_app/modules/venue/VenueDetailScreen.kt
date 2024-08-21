package dev.zotov.prod_app.modules.venue

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.zotov.prod_app.components.Loader
import dev.zotov.prod_app.components.ServerErrorText
import dev.zotov.prod_app.modules.venue.components.RelatedVenuesList
import dev.zotov.prod_app.modules.venue.components.VenueAddress
import dev.zotov.prod_app.modules.venue.components.VenueCategories
import dev.zotov.prod_app.modules.venue.components.VenueContactsView
import dev.zotov.prod_app.modules.venue.components.VenueDetailHeader
import dev.zotov.prod_app.modules.venue.components.VenuePhotos

@Composable
fun VenueDetailScreen(navController: NavController, id: String?) {
    // Создаем view model
    val venueViewModel: VenueViewModel = viewModel(
        factory = VenueViewModelFactory(dev.zotov.prod_app.DI.venuesRepository, dev.zotov.prod_app.DI.launchUri)
    )
    val state = venueViewModel.state.collectAsState().value

    // Делаем запрос на получение venue по id
    LaunchedEffect("venueDetailScreen") {
        if (id != null) {
            venueViewModel.fetchVenue(id)
        }
    }

    fun handleNavigationBack() {
        venueViewModel.handleNavigationBack()
        navController.popBackStack()
    }

    BackHandler { handleNavigationBack() }

    Scaffold(topBar = {
        VenueDetailHeader(
            venueName = if (state is VenueState.Idle) state.venue.name else "",
            onAddTap = {
                if (state is VenueState.Idle) {
                    navController.navigate("/venue/${state.venue.id}/add-venue-task")
                }
            },
            onBack = { handleNavigationBack() }
        )
    }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(bottom = 12.dp)
        ) {
            when (state) {
                is VenueState.Idle -> Content(
                    state = state,
                    openMap = { venueViewModel.openVenueOnMap() },
                    onPhoneClick = { venueViewModel.openPhoneCall() },
                    onFacebookClick = { venueViewModel.openFacebookPage() },
                    onTwitterClick = { venueViewModel.openTwitterPage() },
                    onInstagramClick = { venueViewModel.openInstagramPage() },
                    navController = navController,
                )

                is VenueState.Loading -> Loader()
                is VenueState.Error -> ServerErrorText()
            }
        }
    }


}

@Composable
private fun Content(
    state: VenueState.Idle,
    openMap: () -> Unit,
    onPhoneClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onTwitterClick: () -> Unit,
    onInstagramClick: () -> Unit,
    navController: NavController,
) {
    VenuePhotos(
        bestPhoto = state.venue.bestPhoto,
        photos = state.venue.photos,
    )

    VenueAddress(
        location = state.venue.location,
        openMap = openMap,
    )

    if (state.venue.contacts != null) {
        VenueContactsView(
            contacts = state.venue.contacts!!,
            onPhoneClick = onPhoneClick,
            onFacebookClick = onFacebookClick,
            onTwitterClick = onTwitterClick,
            onInstagramClick = onInstagramClick,
        )
    }

    VenueCategories(
        categories = state.venue.categories,
    )

    RelatedVenuesList(
        relatedVenues = state.venue.related,
        navController = navController,
    )
}