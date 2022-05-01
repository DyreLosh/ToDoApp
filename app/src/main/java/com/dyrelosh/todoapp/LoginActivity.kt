package com.dyrelosh.todoapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dyrelosh.todoapp.ui.theme.ToDoAppTheme
import com.dyrelosh.todoapp.ui.theme.Yellow

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                PrewievLogin()
            }
        }
    }

    @Preview
    @Composable
    fun PrewievLogin() {
        val email = remember { mutableStateOf("")}
        val password = remember { mutableStateOf("")}
        Image(
            painter = painterResource(R.drawable.background_circle_image),
            contentDescription = "",
            Modifier.size(150.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()

        ) {
            Text(
                text = "Welcome Back!",
                fontWeight = Bold,
                fontSize = 22.sp
            )
            Image(
                painter = painterResource(R.drawable.home_image),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp)
                    .padding(10.dp)
            )
            OutlinedTextField(
                value = email.value,
                onValueChange = { newText -> email.value = newText},
                shape = RoundedCornerShape(10) ,

                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = { newText -> password.value = newText},

                modifier = Modifier
                    .padding(top = 10.dp, end = 20.dp, start = 20.dp)
            )
            Text(
                text = "Forgot password",
                color = Yellow,
                fontFamily = FontFamily(Font(R.font.poppins)),
                modifier = Modifier.padding(top = 10.dp)
            )

            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Yellow),
                contentPadding = PaddingValues(vertical = 18.dp),
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 20.sp,
                    fontWeight = Bold,
                    fontFamily = FontFamily(Font(R.font.poppins))
                )
            }
            Text(
                text = "Dont have an account?",
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.poppins))
            )
            Text(
                text = "Sign Up",
                color = Yellow,
                fontFamily = FontFamily(Font(R.font.poppins))
            )

        }
    }
}