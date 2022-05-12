package com.dyrelosh.todoapp.ui.layout.activity

import android.app.AlertDialog
import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.dyrelosh.todoapp.R
import com.dyrelosh.todoapp.common.PreferenceManager
import com.dyrelosh.todoapp.data.https.ApiService
import com.dyrelosh.todoapp.data.model.Token
import com.dyrelosh.todoapp.data.model.UserCreate
import com.dyrelosh.todoapp.common.navigation.StartScreen
import com.dyrelosh.todoapp.ui.theme.ToDoAppTheme
import com.dyrelosh.todoapp.ui.theme.Yellow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RegisterScreen(navController: NavHostController) {
    val context = LocalContext.current
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
        Scaffold(
            backgroundColor = Color.Transparent,
            bottomBar = {
                BottomRegisterBar(
                    name,
                    email,
                    password,
                    confirmPassword,
                    context,
                    navController
                )
            }
        ) {}


    }
}

@Composable
private fun BottomRegisterBar(
    name: MutableState<String>,
    email: MutableState<String>,
    password: MutableState<String>,
    confirmPassword: MutableState<String>,
    context: Context,
    navController: NavController
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                when {
                    name.value.isBlank() && email.value.isBlank()
                            &&password.value.isBlank() && confirmPassword.value.isBlank() ->
                        alertDialogBuilder(context.getString(R.string.blank_field_error), context)
                    !Patterns.EMAIL_ADDRESS.matcher(email.value).matches() ->
                        alertDialogBuilder(context.getString(R.string.true_email_error), context)
                    password.value.length < 8 ->
                        alertDialogBuilder(context.getString(R.string.password_more_8_error), context)
                    password.value != confirmPassword.value ->
                        alertDialogBuilder(context.getString(R.string.confirm_password_error), context)
                    else -> userCreate(name.value, email.value, password.value, context, navController)
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
                text = stringResource(R.string.sign_button),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.poppins))
            )
        }
        Text(
            text = stringResource(R.string.have_account_text),
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.poppins))
        )
        Text(
            text = stringResource(R.string.sign_in_text_button),
            color = Yellow,
            fontFamily = FontFamily(Font(R.font.poppins)),
            modifier = Modifier
                .clickable {
                    navController.navigate(StartScreen.LoginScreen.route)
                }
                .padding(bottom = 10.dp)
        )
    }
}

private fun alertDialogBuilder(message: String, context: Context) {
    AlertDialog.Builder(context)
        .setTitle(context.getString(R.string.error_text))
        .setMessage(message)
        .setPositiveButton("Ok", null)
        .create()
        .show()
}

private fun userCreate(name: String, email: String, password: String, context: Context, navController: NavController) {

    ApiService.retrofit.userCreate(UserCreate(name, email, password)).enqueue(
        object: Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if(response.isSuccessful) {

                    val preferenceManager = PreferenceManager(context)
                    val token = response.body()!!.token
                    preferenceManager.writeLoginPreference(token)
                    navController.navigate(StartScreen.MainScreen.route)
                }
                else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.register_bad_response),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Toast.makeText(
                    context,
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
    }
}
