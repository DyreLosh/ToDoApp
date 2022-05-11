package com.dyrelosh.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dyrelosh.todoapp.ui.layout.screen.ProfileScreen
import com.dyrelosh.todoapp.ui.layout.screen.TasksScreen

@Composable
fun BottomNavigation(navController: NavController) {

    NavHost(navController = navController as NavHostController, startDestination = Screen.TaskScreen.route) {
        composable(Screen.TaskScreen.route) { TasksScreen(navController) }
        composable(Screen.ProfileScreen.route) { ProfileScreen() }
    }
}