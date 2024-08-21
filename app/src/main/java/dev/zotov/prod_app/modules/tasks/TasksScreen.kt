package dev.zotov.prod_app.modules.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.zotov.prod_app.data.models.CustomTask
import dev.zotov.prod_app.data.models.VenueTask
import dev.zotov.prod_app.modules.tasks.components.CustomTaskCard
import dev.zotov.prod_app.modules.tasks.components.TaskListHeader
import dev.zotov.prod_app.modules.tasks.components.VenueTaskCard

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel, navController: NavController) {
    val tasks = tasksViewModel.state.collectAsState().value

    Scaffold(
        topBar = { TaskListHeader(onAddTap = { navController.navigate("/add-custom-task") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            for (task in tasks) {
                when (task) {
                    is VenueTask -> VenueTaskCard(
                        task,
                        onClick = { navController.navigate("/task/${task.id}") }
                    )

                    is CustomTask -> CustomTaskCard(
                        task,
                        onClick = { navController.navigate("/task/${task.id}") }
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}