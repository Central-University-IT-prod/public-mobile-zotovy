package dev.zotov.prod_app.modules.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.zotov.prod_app.data.models.CustomTask
import dev.zotov.prod_app.data.models.Task
import dev.zotov.prod_app.data.models.VenueTask
import dev.zotov.prod_app.components.Button
import dev.zotov.prod_app.components.DateTextField
import dev.zotov.prod_app.components.TextField
import dev.zotov.prod_app.modules.tasks.components.TaskDetailHeader
import dev.zotov.prod_app.modules.venue.components.FullVenueCard
import java.time.ZoneOffset

@Composable
fun TaskDetailScreen(
    tasksViewModel: TasksViewModel,
    navController: NavController,
    task: Task,
) {
    var name by remember { mutableStateOf(task.name) }
    var date by remember { mutableStateOf(task.date) }
    var note by remember { mutableStateOf(if (task is CustomTask) task.note else "") }

    Scaffold(
        topBar = {
            TaskDetailHeader(
                name = task.name,
                onBackClick = { navController.popBackStack() },
                onDeleteClick = {
                    navController.popBackStack()
                    tasksViewModel.remove(task.id)
                }
            )
        }
    ) { innerValues ->
        Column(
            modifier = Modifier
                .padding(innerValues)
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TextField(
                text = name,
                onChange = { name = it },
                modifier = Modifier.padding(bottom = 16.dp),
                placeholder = "Название"
            )

            DateTextField(
                onChange = { date = it },
                modifier = Modifier.padding(bottom = 16.dp),
                initialSelectedDateMillis = date.atStartOfDay(ZoneOffset.UTC)?.toInstant()
                    ?.toEpochMilli(),
            )

            if (task is CustomTask) {
                TextField(
                    text = note,
                    onChange = { note = it },
                    modifier = Modifier.padding(bottom = 16.dp),
                    placeholder = "Заметки",
                    multiline = true,
                )
            }

            if (task is VenueTask) {
                FullVenueCard(
                    venue = task.venue,
                    onClick = {
                        navController.navigate("/venue/${task.venue.id}")
                    }
                )
            }

            Button(
                text = "Сохранить",
                onClick = {
                    tasksViewModel.update(
                        when (task) {
                            is CustomTask -> task.copy(
                                name = name,
                                date = date,
                                note = note
                            )

                            is VenueTask -> task.copy(
                                name = name,
                                date = date,
                            )

                            else -> task
                        }
                    )
                    navController.popBackStack()
                }
            )
        }
    }
}