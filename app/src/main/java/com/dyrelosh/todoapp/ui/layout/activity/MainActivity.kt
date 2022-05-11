package com.dyrelosh.todoapp.ui.layout.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dyrelosh.todoapp.common.navigation.Navigation
import com.dyrelosh.todoapp.ui.layout.activity.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                Navigation()
            }
        }
    }
}
