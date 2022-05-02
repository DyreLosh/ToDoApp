package com.dyrelosh.todoapp.ui.layout.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dyrelosh.todoapp.ui.layout.screen.ProfileScreen
import com.dyrelosh.todoapp.ui.layout.screen.TasksScreen
import com.dyrelosh.todoapp.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme(darkTheme = false) {
                BottomNavigationView()
            }
        }
    }

    @Composable
    fun BottomNavigationView() {
        val navController = rememberNavController()
        val bottomItems = listOf("Tasks", "Profile")

        Scaffold(
            bottomBar = {
                BottomNavigation {
                    bottomItems.forEach { screen ->
                        BottomNavigationItem(
                            selected = true,
                            onClick = {
                                navController.navigate(screen)
                            },
                            label = { Text(text = screen) },
                            icon = {

                            }
                        )
                    }
                }
            }
        ) {

            NavHost(navController = navController, startDestination = "Tasks") {
                composable("Tasks") { TasksScreen() }
                composable("Profile") { ProfileScreen() }
            }
        }
    }
}

