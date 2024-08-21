package dev.zotov.prod_app.data.repositories

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.GsonBuilder
import dev.zotov.prod_app.data.interfaces.TaskRepository
import dev.zotov.prod_app.data.models.CustomTask
import dev.zotov.prod_app.data.models.Task
import dev.zotov.prod_app.data.models.VenueTask
import dev.zotov.prod_app.data.utils.LocalDateAdapter
import java.time.LocalDate

class TaskRepositoryImpl(private val sharedPreferences: SharedPreferences) : TaskRepository {
    private val gson =
        GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateAdapter().nullSafe())
            .create()

    override fun add(task: Task) {
        try {
            val tasks = (sharedPreferences.getStringSet("tasks", emptySet())
                ?: emptyList<String>()).toMutableList()

            tasks.add(0, serializeTask(task))

            with(sharedPreferences.edit()) {
                putStringSet("tasks", tasks.toSet())
                apply()
            }
        } catch (e: Throwable) {
            Log.e("add", "Failed to add task $task", e)
        }
    }

    override fun getAll(): List<Task> {
        return try {
            val tasks = sharedPreferences.getStringSet("tasks", emptySet())
            val list = tasks?.mapNotNull { parseTask(it) } ?: emptyList()
            list.sortedBy { it.date }
        } catch (e: Throwable) {
            Log.e("getAll", "Failed to get all tasks", e)
            emptyList()
        }
    }

    override fun getById(id: String): Task? {
        val tasks = getAll()
        return tasks.firstOrNull { it.id == id }
    }

    override fun remove(id: String) {
        try {
            val tasks = getAll().filter { it.id != id }.map { serializeTask(it) }.toSet()
            with(sharedPreferences.edit()) {
                putStringSet("tasks", tasks)
                apply()
            }
        } catch (e: Throwable) {
            Log.e("remove", "Failed to delete task $id", e)
        }
    }

    override fun update(task: Task) {
        try {
            val tasks = getAll().toMutableList().map { if (task.id == it.id) task else it }
            val raw = tasks.map { serializeTask(it) }.toSet()
            with(sharedPreferences.edit()) {
                putStringSet("tasks", raw)
                apply()
            }
        } catch (e: Throwable) {
            Log.e("update", "Failed to update task $task")
        }
    }

    private fun parseTask(raw: String): Task? {
        try {
            val json = gson.fromJson(raw, Map::class.java)

            return when {
                json["type"] == "custom" -> {
                    gson.fromJson(raw, CustomTask::class.java)
                }

                json["type"] == "venue" -> {
                    gson.fromJson(raw, VenueTask::class.java)
                }

                else -> null
            }
        } catch (e: Throwable) {
            Log.e("parseTask", "Failed to parse raw task $raw", e)
            return null
        }
    }

    private fun serializeTask(task: Task): String? {
        return try {
            val map = gson.fromJson(gson.toJson(task), Map::class.java).toMutableMap()

            map["type"] = when (task) {
                is CustomTask -> "custom"
                is VenueTask -> "venue"
                else -> "unknown"
            }

            gson.toJson(map)
        } catch (e: Throwable) {
            Log.e("serializeTask", "Failed to serialize task $task", e)
            null
        }
    }

}