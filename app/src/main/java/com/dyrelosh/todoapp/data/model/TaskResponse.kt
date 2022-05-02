package com.dyrelosh.todoapp.data.model

data class TaskResponse(
    val userId: Int,
    val id: Int,
    val text: String,
    val isCompleted: Boolean
)
