package com.dyrelosh.todoapp.ui.layout.activity

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dyrelosh.todoapp.navigation.Screen
import com.dyrelosh.todoapp.ui.theme.Yellow

@Composable
fun MainScreen(mainNavController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        com.dyrelosh.todoapp.navigation.BottomNavigation(navController, mainNavController)
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        Screen.TaskScreen,
        Screen.ProfileScreen
    )

    BottomNavigation(
        backgroundColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach{ items ->
            BottomNavigationItem(
                icon ={Icon(painter = painterResource(id = items.image), contentDescription = items.title)},
                onClick = {
                          navController.navigate(items.route) {
                              navController.graph.startDestinationRoute?.let { route ->
                                  popUpTo(route = route) {
                                      saveState = true
                                  }
                              }
                              launchSingleTop = true
                              restoreState = true
                          }
                },
                label = { Text(text = items.title)},
                selectedContentColor = Yellow,
                unselectedContentColor = Color.Black,
                alwaysShowLabel = true,
                selected = currentRoute == items.route
            )
        }
    }
}

