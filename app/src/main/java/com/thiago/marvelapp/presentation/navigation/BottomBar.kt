package com.thiago.marvelapp.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.thiago.marvelapp.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun BottomBar(
    items: List<BottomBarItem>,
    navHostController: NavHostController,
    onItemClick: (String) -> Unit
) {
    val backStackEntry = navHostController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route
    AnimatedVisibility(
        visible = items.map { it.screen.route }.contains(currentRoute),
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        BottomNavigation(
            elevation = 5.dp
        ) {
            items.fastForEach { item ->
                val selected = item.screen.route == currentRoute
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item.screen.route) },
                    alwaysShowLabel = true,
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = stringResource(id = item.label)
                        )
                    },
                    label = {
                        Text(text = stringResource(id = item.label))
                    }
                )
            }
        }
    }
}

internal sealed class BottomBarItem(
    val screen: Screen,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {

    object Characters : BottomBarItem(
        screen = Screen.Characters,
        label = R.string.label_characters,
        icon = R.drawable.ic_characteres_menu_outlined
    )

    object Favorites : BottomBarItem(
        screen = Screen.Favorites,
        label = R.string.label_favorites,
        icon = R.drawable.ic_favorite_menu_outlined
    )

    object About : BottomBarItem(
        screen = Screen.About,
        label = R.string.label_about,
        icon = R.drawable.ic_about_menu_outlined
    )
}