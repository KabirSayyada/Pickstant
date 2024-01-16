package com.savlanet.pickstant.navigation

import androidx.compose.ui.Modifier

fun NavGraphBuilder.addHomeGraph(
    onSnackSelected: (Long, NavBackStackEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    composable(HomeSections.FEED.route) { from ->
        Feed(onSnackClick = { id -> onSnackSelected(id, from) }, modifier)
    }
    composable(HomeSections.SEARCH.route) { from ->
        Search(onSnackClick = { id -> onSnackSelected(id, from) }, modifier)
    }
    composable(HomeSections.CART.route) { from ->
        Cart(onSnackClick = { id -> onSnackSelected(id, from) }, modifier)
    }
    composable(HomeSections.PROFILE.route) {
        Profile(modifier)
    }
}
