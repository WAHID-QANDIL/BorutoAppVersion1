package org.wahid.borutoappversion1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.wahid.borutoappversion1.presentation.screens.home.HomeScreen
import org.wahid.borutoappversion1.presentation.screens.splash.SplashScreen
import org.wahid.borutoappversion1.presentation.screens.welcome.WelcomeScreen
import org.wahid.borutoappversion1.presentation.screens.search.SearchScreen
import org.wahid.borutoappversion1.utils.Constants.HERO_ID


@Composable
fun SetUpNavGraph(
    navHostController: NavHostController,
) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navHostController = navHostController)
        }
        composable(Screen.Welcome.route) {
            WelcomeScreen(navHostController = navHostController)
        }
        composable(
            Screen.Details.route,
            arguments = listOf(navArgument(HERO_ID) { type = NavType.IntType })
        ) { }
        composable(Screen.Home.route) {
            HomeScreen(navHostController = navHostController)
        }
        composable(Screen.Search.route) {
            SearchScreen(navHostController = navHostController)
        }


    }


}