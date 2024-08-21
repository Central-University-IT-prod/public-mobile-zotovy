package dev.zotov.prod_app.data.interfaces

import dev.zotov.prod_app.data.models.Task

interface TaskRepository {

    fun add(task: Task)

    fun getAll(): List<Task>

    fun getById(id: String): Task?

    fun remove(id: String)

    fun update(task: Task)
}