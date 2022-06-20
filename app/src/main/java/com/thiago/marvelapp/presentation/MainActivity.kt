package com.thiago.marvelapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.thiago.marvelapp.presentation.navigation.AppNavigation
import com.thiago.marvelapp.presentation.navigation.BottomBar
import com.thiago.marvelapp.presentation.navigation.BottomBarItem
import com.thiago.marvelapp.ui.theme.MarvelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val items = listOf(BottomBarItem.Characters, BottomBarItem.Favorites, BottomBarItem.About)
        setContent {
            MarvelAppTheme {
                val navHostController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomBar(
                            items = items,
                            navHostController = navHostController,
                            onItemClick = { route ->
                                navHostController.navigate(route)
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation(
                        modifier = Modifier.padding(it),
                        navHostController = navHostController
                    )
                }
            }
        }
    }
}