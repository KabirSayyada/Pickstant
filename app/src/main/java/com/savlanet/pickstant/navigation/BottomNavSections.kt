package com.savlanet.pickstant.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.savlanet.pickstant.MainDestinations

enum class BottomNavSections(
    val title: String,
    val icon: Painter,
    val screen_route: String
) {
    HOME("Home", , MainDestinations.HOME_ROUTE),
    VENT("Vendors", Icons.Outlined., MainDestinations.VENDOR_ROUTE ),
    Search("Search", Icons.Outlined.Search, MainDestinations.SEARCH_ROUTE),
    Cart("Cart", Icons.Outlined.ShoppingCart, MainDestinations.CART_ROUTE),
    YOU("You", Icons.Outlined.AccountCircle, MainDestinations.YOU_ROUTE)

}



@Composable
fun PrumstakeBottomBar(navController: NavController, tabs: Array<BottomNavSections>) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
        ?: BottomNavSections.VENT.screen_route

    val routes = remember { BottomNavSections.values().map { it.screen_route } }
    if (currentRoute in routes) {
        BottomNavigation(
            Modifier.windowInsetsBottomHeight(
                WindowInsets.navigationBars.add(WindowInsets(bottom = 56.dp))
            ),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            tabs.forEach { tab ->
                BottomNavigationItem(
                    icon = { Icon(imageVector = tab.icon, contentDescription = null) },
                    label = { Text(tab.title, fontSize = 10.sp) },
                    selected = currentRoute == tab.screen_route,
                    onClick = {
                        if (tab.screen_route != currentRoute) {
                            navController.navigate(tab.screen_route) {

                                navController.graph.startDestinationRoute?.let { screen_route ->
                                    popUpTo(screen_route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true

                            }
                        }

                    },
                    alwaysShowLabel = true,
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Gray,
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        }
    }
}
