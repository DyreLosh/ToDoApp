package com.dyrelosh.todoapp.common.navigation

sealed class StartScreen(var route: String) {
    object HomeScreen: StartScreen("home_screen")
    object LoginScreen: StartScreen("login_screen")
    object RegisterScreen: StartScreen("register_screen")
    object MainScreen: StartScreen("main_screen")
    object AddNewTaskScreen: StartScreen("add_new_task_screen")
}
