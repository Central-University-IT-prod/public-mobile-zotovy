package dev.zotov.prod_app.modules.tasks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zotov.prod_app.data.interfaces.TaskRepository
import dev.zotov.prod_app.data.interfaces.VenuesRepository
import dev.zotov.prod_app.data.models.CustomTask
import dev.zotov.prod_app.data.models.Task
import dev.zotov.prod_app.data.models.VenueTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

class TasksViewModel(
    private val taskRepository: TaskRepository,
    private val venueRepository: VenuesRepository
) : ViewModel() {
    private val _state = MutableStateFlow<List<Task>>(emptyList())
    val state: StateFlow<List<Task>> get() = _state

    init {
        _state.value = taskRepository.getAll()
    }

    private fun create(task: Task) {
        taskRepository.add(task)
        _state.value = (listOf(task) + state.value).sortedBy { it.date }
    }

    fun createCustomTask(name: String, date: LocalDate, note: String) {
        val task = CustomTask(
            id = UUID.randomUUID().toString(),
            name = name,
            date = date,
            note = note,
        )

        create(task)
    }

    fun createVenueTask(name: String, date: LocalDate, venueId: String) {
        viewModelScope.launch {
            val venue = venueRepository.getById(venueId)

            if (venue != null) {
                Log.d("createVenueTask", venue.toString())
                val task = VenueTask(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    date = date,
                    venue = venue,
                )

                create(task)
            }
        }
    }

    fun remove(id: String) {
        taskRepository.remove(id)
        _state.value = state.value.filter { it.id != id }
    }

    fun update(task: Task) {
        taskRepository.update(task)
        _state.value = state.value.map { if (task.id == it.id) task else it }.sortedBy { it.date }
    }

    fun getById(id: String) = taskRepository.getById(id)
}