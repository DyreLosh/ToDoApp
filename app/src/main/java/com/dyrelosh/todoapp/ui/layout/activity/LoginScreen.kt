package com.dyrelosh.todoapp.ui.layout.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.dyrelosh.todoapp.R
import com.dyrelosh.todoapp.common.PreferenceManager
import com.dyrelosh.todoapp.data.https.ApiService
import com.dyrelosh.todoapp.data.model.Token
import com.dyrelosh.todoapp.data.model.UserLogin
import com.dyrelosh.todoapp.navigation.StartScreen
import com.dyrelosh.todoapp.ui.theme.ToDoAppTheme
import com.dyrelosh.todoapp.ui.theme.Yellow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection
@SuppressLint("StaticFieldLeak")
@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
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
            text = stringResource(R.string.login_main_text),
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
            placeholder = { Text(text = stringResource(R.string.email_hint))},
            singleLine = true,
            shape = RoundedCornerShape(50) ,
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "")},
            modifier = Modifier
                .padding(top = 20.dp, start = 40.dp, end = 40.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { newText -> password.value = newText},
            placeholder = { Text(text = stringResource(R.string.password_hint))},
            singleLine = true,
            shape = RoundedCornerShape(50) ,
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "")},
            modifier = Modifier
                .padding(top = 10.dp, end = 40.dp, start = 40.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.forgot_password_text_button),
            color = Yellow,
            fontFamily = FontFamily(Font(R.font.poppins)),
            modifier = Modifier.padding(top = 10.dp)
        )
        Scaffold(
            bottomBar = { BottomBar(email, password, context, navController) }) {}
    }

}

@Composable
private fun BottomBar(email: MutableState<String>, password: MutableState<String>, context: Context, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                when {
                    !Patterns.EMAIL_ADDRESS.matcher(email.value).matches() ->
                        alertBuilderLogin(context.getString(R.string.true_email_error), context)
                    password.value.length < 8 && password.value.isBlank() ->
                        alertBuilderLogin(context.getString(R.string.password_more_8_error), context)
                    else ->  userLogin(email.value, password.value, context, navController)
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
                fontWeight = Bold,
                color = Color.Black,
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
            fontFamily = FontFamily(Font(R.font.poppins)),
            modifier = Modifier
                .clickable {
                    navController.navigate(StartScreen.RegisterScreen.route)
                }
                .padding(bottom = 10.dp)
        )
    }
}

private fun alertBuilderLogin(message: String, context: Context) {
    android.app.AlertDialog.Builder(context)
        .setTitle(context.getString(R.string.error_text))
        .setMessage(message)
        .setPositiveButton("Ok", null)
        .create()
        .show()
}

private fun userLogin(email: String, password: String, context: Context, navController: NavController) {
    ApiService.retrofit.userLogin(UserLogin(email, password)).enqueue(
        object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                when(response.code()) {
                    HttpsURLConnection.HTTP_OK -> {
                        val token = response.body()!!.token
                        val preferenceManager = PreferenceManager(context)
                        preferenceManager.writeLoginPreference(token)
                        navController.navigate(StartScreen.MainScreen.route)

                    }
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.login_unauthorized),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    HttpsURLConnection.HTTP_BAD_REQUEST -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.login_bad_request),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    )
}
