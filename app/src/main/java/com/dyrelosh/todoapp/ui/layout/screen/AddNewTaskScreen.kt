package com.dyrelosh.todoapp.ui.layout.screen

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dyrelosh.todoapp.R
import com.dyrelosh.todoapp.common.PreferenceManager
import com.dyrelosh.todoapp.data.https.ApiService
import com.dyrelosh.todoapp.data.model.NewTask
import com.dyrelosh.todoapp.ui.theme.Yellow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddNewTaskScreen(navController: NavHostController) {
    val newText = remember { mutableStateOf("")}
    val context = LocalContext.current
    val preferenceManager = PreferenceManager(context)
    Column() {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "",
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp)
                .clickable { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = newText.value,
            onValueChange = { new -> newText.value = new},
            shape = RoundedCornerShape(10) ,
            placeholder = { Text(text = "Введите текст заметки")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .height(100.dp)
        )

        Scaffold(bottomBar = {
            Button(
                onClick = {
                    if(newText.value.isNotBlank()) {
                        ApiService.retrofit.addNewTask(
                            "Bearer ${preferenceManager.readLoginPreference()}",
                            NewTask(newText.value)
                        ).enqueue( object : Callback<Unit> {
                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                if (response.isSuccessful) {
                                    Toast.makeText(
                                        context,
                                        "Задача успешно создана",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.popBackStack()
                                }
                                else {
                                    Toast.makeText(
                                        context,
                                        "Отсутсвует токен доступа",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<Unit>, t: Throwable) {
                                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
                            }

                        }

                        )

                    }
                    else {
                        AlertDialog.Builder(context).setPositiveButton("Ok", null).setMessage("Введите текст").create().show()
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Yellow),
                contentPadding = PaddingValues(vertical = 18.dp),
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "Добавить",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.poppins))
                )
            }
        }) {}
    }

}
