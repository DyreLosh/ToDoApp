package com.dyrelosh.todoapp.ui.layout.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dyrelosh.todoapp.R
import com.dyrelosh.todoapp.data.https.ApiService
import com.dyrelosh.todoapp.data.model.Token
import com.dyrelosh.todoapp.data.model.UserCreate
import com.dyrelosh.todoapp.ui.theme.ToDoAppTheme
import com.dyrelosh.todoapp.ui.theme.Yellow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                RegisterUI()
            }
        }
    }
    @Composable
    fun RegisterUI() {
        val name = remember { mutableStateOf("")}
        val email = remember { mutableStateOf("")}
        val password = remember { mutableStateOf("")}
        val confirmPassword = remember { mutableStateOf("")}
        Image(
            painter = painterResource(R.drawable.background_circle_image),
            contentDescription = "background",
            Modifier.size(150.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 120.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.register_main_text),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.poppins))
            )
            Text(
                text = stringResource(R.string.register_second_text),
                fontFamily = FontFamily(Font(R.font.poppins))
            )
            OutlinedTextField(
                value = name.value,
                onValueChange = { newText -> name.value = newText},
                placeholder = { Text(text = stringResource(R.string.name_hint))},
                singleLine = true,
                shape = RoundedCornerShape(50) ,
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "")},
                modifier = Modifier
                    .padding(top = 50.dp, start = 40.dp, end = 40.dp)
                    .fillMaxWidth()

            )
            OutlinedTextField(
                value = email.value,
                onValueChange = { newText -> email.value = newText},
                placeholder = { Text(text = stringResource(R.string.email_hint))},
                singleLine = true,
                shape = RoundedCornerShape(50) ,
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "")},
                modifier = Modifier
                    .padding(top = 10.dp, start = 40.dp, end = 40.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = { newText -> password.value = newText},
                shape = RoundedCornerShape(50) ,
                placeholder = { Text(text = stringResource(R.string.password_hint))},
                singleLine = true,
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "")},
                modifier = Modifier
                    .padding(top = 10.dp, start = 40.dp, end = 40.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = confirmPassword.value,
                onValueChange = { newText -> confirmPassword.value = newText},
                shape = RoundedCornerShape(50) ,
                placeholder = { Text(text = stringResource(R.string.confirm_password_hint))},
                singleLine = true,
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "")},
                modifier = Modifier
                    .padding(top = 10.dp, start = 40.dp, end = 40.dp)
                    .fillMaxWidth()
            )
            Scaffold (bottomBar = { BottomRegisterBar(name, email, password, confirmPassword) }) {}

        }
    }

    @Composable
    private fun BottomRegisterBar(name: MutableState<String>, email: MutableState<String>, password: MutableState<String>, confirmPassword: MutableState<String>) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = {
                    when {
                        name.value.isBlank() && email.value.isBlank()
                                &&password.value.isBlank() && confirmPassword.value.isBlank() ->
                            alertDialogBuilder(getString(R.string.blank_field_error))
                        !Patterns.EMAIL_ADDRESS.matcher(email.value).matches() ->
                            alertDialogBuilder(getString(R.string.true_email_error))
                        password.value.length < 8 ->
                            alertDialogBuilder(getString(R.string.password_more_8_error))
                        password.value != confirmPassword.value ->
                            alertDialogBuilder(getString(R.string.confirm_password_error))
                        else -> userCreate(name.value, email.value, password.value)
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
                    color = Color.Black,
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
                fontFamily = FontFamily(Font(R.font.poppins)),
                modifier = Modifier
                    .clickable {
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    }
                    .padding(bottom = 10.dp)
            )
        }
    }

    private fun alertDialogBuilder(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Ошибка")
            .setMessage(message)
            .setPositiveButton("Ok", null)
            .create()
            .show()
    }

    private fun userCreate(name: String, email: String, password: String) {

        ApiService.retrofit.userCreate(UserCreate(name, email, password)).enqueue(
            object: Callback<Token> {
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    if(response.isSuccessful) {
                        startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                        val token = response.body()!!.token
                    }
                    else {
                        Toast.makeText(
                            this@RegisterActivity,
                            getString(R.string.register_bad_response),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Toast.makeText(
                        this@RegisterActivity,
                        t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun DefaultPreview() {
        ToDoAppTheme {
            RegisterUI()
        }
    }
}

