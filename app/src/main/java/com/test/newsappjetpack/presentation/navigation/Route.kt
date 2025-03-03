package com.test.newsappjetpack.presentation.navigation

sealed class Route (
    val route: String
) {
    object OnBoardingScreen : Route("onBoardingScreen")
    object HomeScreen : Route("homeScreen")
    object BookMarkScreen : Route("bookmarkScreen")
    object ProfileScreen : Route("profileScreen")
    object DetailsScreen : Route("detailsScreen")
    object AppStartNavigation : Route("appStartNavigation")
    object NewsNavigation : Route("newsNavigation")
    object NewsNavigatorScreen : Route("newsNavigator")
}