package org.wahid.borutoappversion1.navigation

import org.wahid.borutoappversion1.utils.Constants.HERO_ID

sealed class Screen(val route:String) {
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{$HERO_ID}") {
        fun passHeroId(heroId: Int): String {
            return "details_screen/$heroId"
        }
    }

    data object Search : Screen("search_screen")

}