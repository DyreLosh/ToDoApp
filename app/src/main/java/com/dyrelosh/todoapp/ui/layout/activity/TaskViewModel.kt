package com.dyrelosh.todoapp.ui.layout.activity

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dyrelosh.todoapp.common.PreferenceManager
import com.dyrelosh.todoapp.data.https.ApiService
import com.dyrelosh.todoapp.data.model.TaskResponse
import kotlinx.coroutines.launch

class TaskViewModel(context: Context) : ViewModel() {
    var taskListResponse: List<TaskResponse> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    private val preferenceManager = PreferenceManager(context)

}