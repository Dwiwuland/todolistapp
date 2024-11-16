package com.example.todolist

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: String,
    val deadline: String,
    val completed: Boolean
)

