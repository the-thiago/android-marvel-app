package com.thiago.marvelapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thiago.marvelapp.presentation.about.AboutScreen
import com.thiago.marvelapp.presentation.characters.CharactersScreen
import com.thiago.marvelapp.presentation.favorites.FavoritesScreen

@Composable
internal fun AppNavigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Characters.route
    ) {
        composable(Screen.Characters.route) {
            CharactersScreen()
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen()
        }
        composable(Screen.About.route) {
            AboutScreen()
        }
    }
}