package com.dyrelosh.todoapp.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dyrelosh.todoapp.ui.layout.screen.HomeAct
import com.dyrelosh.todoapp.ui.layout.activity.LoginScreen
import com.dyrelosh.todoapp.ui.layout.activity.MainScreen
import com.dyrelosh.todoapp.ui.layout.activity.RegisterScreen
import com.dyrelosh.todoapp.ui.layout.screen.AddNewTaskScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = StartScreen.HomeScreen.route) {
        composable(StartScreen.HomeScreen.route) { HomeAct(navController) }
        composable(StartScreen.LoginScreen.route) { LoginScreen(navController)}
        composable(StartScreen.RegisterScreen.route) { RegisterScreen(navController)}
        composable(StartScreen.MainScreen.route) { MainScreen(navController) }
        composable(StartScreen.AddNewTaskScreen.route) { AddNewTaskScreen(navController)}
    }
}
