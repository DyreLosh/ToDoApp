package com.dyrelosh.todoapp

import android.app.AlertDialog
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dyrelosh.todoapp.ui.theme.ToDoAppTheme
import com.dyrelosh.todoapp.ui.theme.Yellow

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                RegisterUI()
            }
        }
    }
}

@Composable
fun RegisterUI() {
    val name = remember { mutableStateOf("")}
    val email = remember { mutableStateOf("")}
    val password = remember { mutableStateOf("")}
    var confirmPassword = remember { mutableStateOf("")}
    Image(
        painter = painterResource(R.drawable.background_circle_image),
        contentDescription = "background",
        Modifier.size(150.dp)
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 120.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Welcome Onboard!",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.poppins))
        )
        Text(
            text = "We help yiu meet up you tasks on time.",
            fontFamily = FontFamily(Font(R.font.poppins))
        )
        OutlinedTextField(
            value = name.value,
            onValueChange = { newText -> name.value = newText},
            shape = RoundedCornerShape(10) ,

            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
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
            shape = RoundedCornerShape(10) ,

            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
        )
        OutlinedTextField(
            value = confirmPassword.value,
            onValueChange = { newText -> confirmPassword.value = newText},
            shape = RoundedCornerShape(10) ,

            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
        )
        Button(
            onClick = {
                if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
                    && password.value.length > 8
                ) {

                }
                else {


                }

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
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.poppins))
            )
        }
        Text(
            text = "Already have an account?",
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.poppins))
        )
        Text(
            text = "Sign In",
            color = Yellow,
            fontFamily = FontFamily(Font(R.font.poppins))
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ToDoAppTheme {
        RegisterUI()
    }
}