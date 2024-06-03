package com.example.trackz.components

sealed class Activity(val route: String) {
    object Tracks : Activity("tracks")
    object StopWatch : Activity("stopwatch")
    object Favourites : Activity("favourites")
    object Camera : Activity("camera")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {  arg ->
                append("/$arg")
            }
        }
    }
}