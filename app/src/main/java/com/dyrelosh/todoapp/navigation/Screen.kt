package com.dyrelosh.todoapp.navigation

import com.dyrelosh.todoapp.R

sealed class Screen(var route: String, var image: Int, var title: String) {
    object TaskScreen: Screen("task_screen", R.drawable.ic_check_box, "Заметки")
    object ProfileScreen: Screen("profile_screen", R.drawable.ic_person, "Профиль")
}
