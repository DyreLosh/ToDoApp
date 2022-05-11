package com.dyrelosh.todoapp.common.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dyrelosh.todoapp.ui.layout.screen.ProfileScreen
import com.dyrelosh.todoapp.ui.layout.screen.TasksScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomNavigation(navController: NavController, mainNavController: NavController) {

    NavHost(navController = navController as NavHostController, startDestination = Screen.TaskScreen.route) {
        composable(Screen.TaskScreen.route) { TasksScreen(navController, mainNavController) }
        composable(Screen.ProfileScreen.route) { ProfileScreen() }
    }
}