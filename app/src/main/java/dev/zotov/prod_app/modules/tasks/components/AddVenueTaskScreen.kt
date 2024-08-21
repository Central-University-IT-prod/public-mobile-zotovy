package dev.zotov.prod_app.modules.tasks.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.zotov.prod_app.components.Button
import dev.zotov.prod_app.components.DateTextField
import dev.zotov.prod_app.components.TextField
import dev.zotov.prod_app.modules.tasks.TasksViewModel
import java.time.LocalDate

@Composable
fun AddVenueTaskScreen(
    tasksViewModel: TasksViewModel,
    navController: NavController,
    venueId: String,
) {
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf<LocalDate?>(null) }

    Scaffold(
        topBar = {
            AddTaskHeader(
                onBack = { navController.popBackStack() },
                title = "Создание активности"
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
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
            )

            Button(
                text = "Создать",
                onClick = {
                    if (name.isNotBlank() && date != null) {
                        tasksViewModel.createVenueTask(
                            name = name,
                            date = date!!,
                            venueId = venueId,
                        )
                        navController.popBackStack()
                    }
                }
            )
        }
    }

}