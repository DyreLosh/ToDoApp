package com.dyrelosh.todoapp.ui.layout.activity

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dyrelosh.todoapp.R
import com.dyrelosh.todoapp.common.PreferenceManager
import com.dyrelosh.todoapp.common.navigation.StartScreen
import com.dyrelosh.todoapp.ui.theme.Yellow

@Composable
fun HomeAct(navController: NavHostController) {
    val context = LocalContext.current
    Image(
        painter = painterResource(R.drawable.background_circle_image),
        contentDescription = "",
        Modifier.size(150.dp)
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 150.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.home_image),
            contentDescription = "Image",
            Modifier.size(180.dp)
        )
        Text(
            text = context.getString(R.string.home_main_text),
            fontSize = 18.sp,
            fontWeight = Bold,
            fontFamily = FontFamily(Font(R.font.poppins)),
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = context.getString(R.string.home_second_text),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 20.dp)
        )
        Scaffold(
            bottomBar = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            val preferenceManager = PreferenceManager(context)
                            if(preferenceManager.readLoginPreference().isNotBlank()) {
                                navController.navigate(StartScreen.MainScreen.route)
                            }
                            else {
                                navController.navigate(StartScreen.LoginScreen.route)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Yellow),
                        contentPadding = PaddingValues(vertical = 18.dp),
                        shape = RoundedCornerShape(20),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 0.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.splash_main_text),
                            fontSize = 20.sp,
                            fontWeight = Bold,
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.poppins))
                        )
                    }
                }}
        ) {
        }
    }
}