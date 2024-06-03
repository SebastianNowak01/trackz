package com.example.trackz.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

/**
 * Composable function representing the navigation for mobile devices.
 */
@Composable
fun NavigationMobile() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreenMobile(navController = navController)
        }
        composable(route = Screen.DetailScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            TrackDescriptionMobile(track = entry.arguments?.getString("name"))
        }
    }
}