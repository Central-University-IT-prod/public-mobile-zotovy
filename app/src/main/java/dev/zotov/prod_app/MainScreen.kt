package dev.zotov.prod_app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.zotov.prod_app.components.BottomNavBar
import dev.zotov.prod_app.components.NavPage
import dev.zotov.prod_app.modules.home.HomeScreen
import dev.zotov.prod_app.modules.map.MapScreen
import dev.zotov.prod_app.modules.profile.ProfileScreen
import dev.zotov.prod_app.modules.tasks.TasksScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    var currentPage by rememberSaveable { mutableStateOf(NavPage.Home) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentPage = currentPage,
                onChange = { currentPage = it }
            )
        }
    ) {
        Box(modifier = Modifier.padding(bottom = 80.dp)) {
            when (currentPage) {
                NavPage.Home -> HomeScreen(
                    viewModel = DI.homeViewModel,
                    navController = navController
                )
                NavPage.MyActivities -> TasksScreen(
                    tasksViewModel = DI.tasksViewModel,
                    navController = navController
                )
                NavPage.Map -> MapScreen(
                    mapViewModel = DI.mapViewModel,
                    navController = navController,
                )
                NavPage.Profile -> ProfileScreen(
                    viewModel = DI.profileViewModel,
                    navController = navController,
                )
            }
        }
    }

}