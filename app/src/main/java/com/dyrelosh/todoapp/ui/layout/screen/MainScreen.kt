package com.dyrelosh.todoapp.ui.layout.activity

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dyrelosh.todoapp.common.navigation.Screen
import com.dyrelosh.todoapp.ui.theme.Yellow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(mainNavController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        Column(modifier = Modifier.padding(bottom = 50.dp)) {
            com.dyrelosh.todoapp.common.navigation.BottomNavigation(navController, mainNavController)

        }
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

