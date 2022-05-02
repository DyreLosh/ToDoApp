package com.dyrelosh.todoapp.ui.layout.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dyrelosh.todoapp.R
import com.dyrelosh.todoapp.ui.theme.ToDoAppTheme
import com.dyrelosh.todoapp.ui.theme.Yellow

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                HomeAct()

            }
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun HomeAct() {
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
                text = getString(R.string.home_main_text),
                fontSize = 18.sp,
                fontWeight = Bold,
                fontFamily = FontFamily(Font(R.font.poppins)),
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = getString(R.string.home_second_text),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 20.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Yellow),
                    contentPadding = PaddingValues(vertical = 18.dp),
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 0.dp)
                ) {
                    Text(
                        text = "GET STARTED",
                        fontSize = 20.sp,
                        fontWeight = Bold,
                        fontFamily = FontFamily(Font(R.font.poppins))
                    )
                }
            }
        }

    }
}