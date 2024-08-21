package dev.zotov.prod_app

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.zotov.prod_app.modules.tasks.TaskDetailScreen
import dev.zotov.prod_app.modules.tasks.components.AddCustomTaskScreen
import dev.zotov.prod_app.modules.tasks.components.AddVenueTaskScreen
import dev.zotov.prod_app.modules.venue.VenueDetailScreen

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Router() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/main") {
        composable(
            route = "/main",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 200),
                )
            },
        ) {
            MainScreen(
                navController = navController
            )
        }
        composable(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            route = "/venue/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            VenueDetailScreen(navController, backStackEntry.arguments?.getString("id"))
        }

        composable(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            route = "/venue/{id}/add-venue-task",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
        ) { backStackEntry ->
            AddVenueTaskScreen(
                tasksViewModel = DI.tasksViewModel,
                navController = navController,
                venueId = backStackEntry.arguments?.getString("id") ?: ""
            )
        }

        composable(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            route = "/add-custom-task",
        ) {
            AddCustomTaskScreen(
                tasksViewModel = DI.tasksViewModel,
                navController = navController,
            )
        }

        composable(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 200),
                )
            },
            route = "/task/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->

            val taskId = backStackEntry.arguments?.getString("id") ?: ""
            val task = remember { DI.tasksViewModel.getById(taskId) }

            if (task != null) {
                TaskDetailScreen(
                    tasksViewModel = DI.tasksViewModel,
                    task = task,
                    navController = navController,
                )
            }
        }
    }
}