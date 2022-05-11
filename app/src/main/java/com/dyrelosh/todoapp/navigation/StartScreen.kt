package com.dyrelosh.todoapp.navigation

sealed class StartScreen(var route: String) {
    object HomeScreen: StartScreen("home_screen")
    object LoginScreen: StartScreen("login_screen")
    object RegisterScreen: StartScreen("register_screen")
    object MainScreen: StartScreen("main_screen")
}
