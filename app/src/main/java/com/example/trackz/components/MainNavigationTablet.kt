package com.example.trackz.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavigationTablet(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Activity.Tracks.route) {
        composable(Activity.Tracks.route) {
            MainScreenTablet()
        }
        composable(Activity.StopWatch.route) {
            val stopWatch = remember { StopWatch() }
            StopWatchDisplay(
                formattedTime = stopWatch.formattedTime,
                onStartClick = stopWatch::start,
                onPauseClick = stopWatch::pause,
                onResetClick = stopWatch::reset,
                onSaveClick = stopWatch::getCurrentTime
            )
        }
        composable(Activity.Favourites.route) {
            FavouritesTablet()
        }
    }
}