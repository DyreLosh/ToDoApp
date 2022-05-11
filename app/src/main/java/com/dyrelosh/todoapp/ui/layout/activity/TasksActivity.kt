package com.dyrelosh.todoapp.ui.layout.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dyrelosh.todoapp.R
import com.dyrelosh.todoapp.data.https.ApiService
import com.dyrelosh.todoapp.data.model.TaskResponse
import com.dyrelosh.todoapp.ui.layout.theme.ToDoAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TasksActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
               TopTasksBar()
                val mainViewModel = TaskViewModel(this)
                TasksList(taskList = mainViewModel.taskListResponse)
            }
        }
    }
    @Composable
    fun TasksList(taskList: List<TaskResponse>) {
        LazyColumn() {

            itemsIndexed(items = taskList) {index, item ->
                TaskCard(taskResponse = item)
            }
        }
    }
    @Composable
    fun TopTasksBar(
    ) {
        TopAppBar(
            backgroundColor = Color.White
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Tasks List",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    modifier = Modifier.padding(start = 10.dp)
                )
                Icon(
                    Icons.Filled.ExitToApp,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clickable {
                            finish()
                        }
                )
            }

        }
    }


    @Composable
    fun TaskCard(taskResponse: TaskResponse) {
        Row() {
            Checkbox(
                checked = taskResponse.isCompleted,
                onCheckedChange = { },
            )
            Text(text = taskResponse.text)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ToDoAppTheme {
            TopTasksBar()
           // TaskCard()
        }
    }
}



