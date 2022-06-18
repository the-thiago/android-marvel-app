package com.thiago.marvelapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thiago.marvelapp.presentation.about.AboutScreen
import com.thiago.marvelapp.presentation.characters.CharactersScreen
import com.thiago.marvelapp.presentation.favorites.FavoritesScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun AppNavigation(navHostController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Characters.route,
        modifier = modifier
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