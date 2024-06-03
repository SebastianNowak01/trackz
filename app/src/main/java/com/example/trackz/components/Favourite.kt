package com.example.trackz.components

/**
 * Sealed class that represents the different favourite screens.
 * @param route the route of the activity
 */
sealed class Favourite(val route: String) {
    object FavMainScreen : Screen("favourite_main_screen")
    object FavDetailScreen : Screen("favourite_detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {  arg ->
                append("/$arg")
            }
        }
    }
}