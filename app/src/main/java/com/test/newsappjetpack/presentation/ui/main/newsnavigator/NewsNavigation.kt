package com.test.newsappjetpack.presentation.ui.main.newsnavigator


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.test.newsappjetpack.R
import com.test.newsappjetpack.presentation.navigation.Route
import com.test.newsappjetpack.presentation.ui.main.newsnavigator.components.BottomNavigationItem
import com.test.newsappjetpack.presentation.ui.main.newsnavigator.components.NewsBottomNavigation
import com.test.newsappjetpack.presentation.ui.main.screens.bookmarks.BookMarkScreen
import com.test.newsappjetpack.presentation.ui.main.screens.home.newsdetails.DetailsNewsScreen
import com.test.newsappjetpack.presentation.ui.main.screens.home.HomeScreen
import com.test.newsappjetpack.presentation.ui.main.screens.profile.ProfileScreen

@Composable
fun NewsNavigator() {
    val bottomNavigationList = listOf(
        BottomNavigationItem(
            icon = R.drawable.ic_home,
            text = stringResource(R.string.home_nav_item)
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_bookmark,
            text = stringResource(R.string.bookmark_nav_item)
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_profile,
            text = stringResource(R.string.profile_nav_item)
        ),
    )
    val bottomNavigationItems = remember {
        bottomNavigationList
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.BookMarkScreen.route -> 1
        Route.ProfileScreen.route -> 2
        else -> 0
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        //  if (isBottomBarVisible) {
        NewsBottomNavigation(
            items = bottomNavigationItems,
            selectedItem = selectedItem,
            onItemClick = { index ->
                when (index) {
                    0 -> navigateToTab(
                        navController = navController,
                        route = Route.HomeScreen.route
                    )

                    1 -> navigateToTab(
                        navController = navController,
                        route = Route.BookMarkScreen.route
                    )

                    2 -> navigateToTab(
                        navController = navController,
                        route = Route.ProfileScreen.route
                    )
                }
            }
        )
        //    }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                HomeScreen()
            }
            composable(route = Route.BookMarkScreen.route) {
                BookMarkScreen()
            }
            composable(route = Route.DetailsScreen.route) {
                DetailsNewsScreen()
            }
            composable(route = Route.ProfileScreen.route) {
                ProfileScreen()
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            popUpTo(screenRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}