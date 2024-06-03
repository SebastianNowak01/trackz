package com.example.trackz.components

/**
 * Sealed class representing the different screens in NavigationMobile.
 *
 * @param route The route of the screen.
 */
sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {  arg ->
                append("/$arg")
            }
        }
    }
}