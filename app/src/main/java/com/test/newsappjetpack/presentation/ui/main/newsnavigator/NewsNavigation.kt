package com.test.newsappjetpack.presentation.ui.main.newsnavigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.test.newsappjetpack.R
import com.test.newsappjetpack.presentation.models.NewsUI
import com.test.newsappjetpack.presentation.navigation.Route
import com.test.newsappjetpack.presentation.ui.main.newsnavigator.components.BottomNavigationItem
import com.test.newsappjetpack.presentation.ui.main.newsnavigator.components.NewsBottomNavigation
import com.test.newsappjetpack.presentation.ui.main.screens.bookmarks.BookMarkScreen
import com.test.newsappjetpack.presentation.ui.main.screens.bookmarks.BookMarkViewModel
import com.test.newsappjetpack.presentation.ui.main.screens.home.newsdetails.DetailsNewsScreen
import com.test.newsappjetpack.presentation.ui.main.screens.home.HomeScreen
import com.test.newsappjetpack.presentation.ui.main.screens.home.HomeViewModel
import com.test.newsappjetpack.presentation.ui.main.screens.home.newsdetails.DetailsEvent
import com.test.newsappjetpack.presentation.ui.main.screens.home.newsdetails.DetailsViewModel
import com.test.newsappjetpack.presentation.ui.main.screens.profile.ProfileScreen
import com.test.newsappjetpack.utils.Constants

@Composable
fun NewsNavigator() {
    val bottomNavigationList = listOf(
        BottomNavigationItem(
            icon = R.drawable.ic_home, text = stringResource(R.string.home_nav_item)
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_bookmark_outline, text = stringResource(R.string.bookmark_nav_item)
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_profile, text = stringResource(R.string.profile_nav_item)
        ),
    )
    val bottomNavigationItems = remember {
        bottomNavigationList
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    // Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route || backStackState?.destination?.route == Route.BookMarkScreen.route || backStackState?.destination?.route == Route.ProfileScreen.route
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.BookMarkScreen.route -> 1
        Route.ProfileScreen.route -> 2
        else -> 0
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            NewsBottomNavigation(items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController, route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController, route = Route.BookMarkScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController, route = Route.ProfileScreen.route
                        )
                    }
                })
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()


        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val news = viewModel.newsState.collectAsLazyPagingItems()
                HomeScreen(news, navigateToDetails = { newsUI ->
                    navigateToDetails(
                        navController = navController, newsUI = newsUI
                    )
                })
            }
            composable(route = Route.BookMarkScreen.route) {
                val viewModel: BookMarkViewModel = hiltViewModel()
                viewModel.getBookMarks()
                val bookMarksList = viewModel.newsState.collectAsState()
                BookMarkScreen(bookMarksList.value, navigateToDetails = { newsUI ->
                    navigateToDetails(
                        navController = navController, newsUI = newsUI
                    )
                })
            }
            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<NewsUI?>(Constants.NEWS_UI_KEY)
                    ?.let { newsUI ->
                        viewModel.checkBookMark(newsUI = newsUI)
                        val isBookMarkState = viewModel.isBookMarkState.collectAsState()

                        DetailsNewsScreen(news = newsUI, isBookMarkState.value, event = { event ->
                            if (event is DetailsEvent.SaveNews) {
                                viewModel.addBookMark(newsUI)
                            } else {
                                viewModel.deleteBookMark(newsUI)
                            }
                        }, navigateUp = { navController.navigateUp() })
                    }
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

private fun navigateToDetails(navController: NavController, newsUI: NewsUI) {
    navController.currentBackStackEntry?.savedStateHandle?.set(Constants.NEWS_UI_KEY, newsUI)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}