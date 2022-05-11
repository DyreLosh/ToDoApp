package com.dyrelosh.todoapp.ui.layout.screen

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.dyrelosh.todoapp.R
import com.dyrelosh.todoapp.common.PreferenceManager
import com.dyrelosh.todoapp.data.https.ApiService
import com.dyrelosh.todoapp.data.model.TaskResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

@Composable
fun TasksScreen(navController: NavHostController, mainNavController: NavController) {

    val context = LocalContext.current
    val _taskList = remember { mutableStateOf<List<TaskResponse>?>(null)}
    var taskList = _taskList.value
    val preferenceManager = PreferenceManager(context)
    ApiService.retrofit.getTasks("Bearer ${preferenceManager.readLoginPreference()}").enqueue(
        object: Callback<List<TaskResponse>> {
            override fun onResponse(
                call: Call<List<TaskResponse>>,
                response: Response<List<TaskResponse>>
            ) {
                when(response.code()) {
                    HttpsURLConnection.HTTP_OK -> {
                        _taskList.value = response.body()!!
                    }
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        Toast.makeText(context, "Пользователь не авторизован", Toast.LENGTH_SHORT).show()
                        preferenceManager.deleteLoginPreference()
                        navController.popBackStack()
                    }
                    HttpsURLConnection.HTTP_BAD_REQUEST -> {
                        Toast.makeText(context, "Ошибка запроса", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<TaskResponse>>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        }
    )

    Box {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            TopTasksBar(context, mainNavController)
            if(taskList != null) {
                for (item in taskList) {
                    TaskCard(taskResponse = item)
                }
            } else {
                Text(text = "Загрузка..")
            }
        }
    }
}
@Composable
fun TaskCard(taskResponse: TaskResponse) {
    Row() {
        Text(text = taskResponse.text)
    }
}
@Composable
fun TopTasksBar(context:Context, mainNavController: NavController){
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
                        val preferenceManager = PreferenceManager(context)
                        preferenceManager.deleteLoginPreference()
                        mainNavController.popBackStack()
                    }
            )
        }

    }
}