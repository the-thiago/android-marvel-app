package com.thiago.marvelapp.presentation.navigation

internal sealed class Screen(val route: String) {
    object Characters : Screen(route = "characters")
    object Favorites : Screen(route = "favorites")
    object About : Screen(route = "about")
}