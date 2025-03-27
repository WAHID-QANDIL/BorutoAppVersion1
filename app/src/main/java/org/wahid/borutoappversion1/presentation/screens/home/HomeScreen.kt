package org.wahid.borutoappversion1.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import org.wahid.borutoappversion1.navigation.Screen
import org.wahid.borutoappversion1.presentation.common.ContentList
import org.wahid.borutoappversion1.presentation.screens.home.component.HomeTopAppBar


@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {
    val heroesFlow = remember {
        viewModel.heroes
    }
    val heroes = heroesFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopAppBar { navHostController.navigate(Screen.Search.route) }
        }
    ) {
        val padding = it
        ContentList(
            modifier = Modifier.padding(padding),
            heroes = heroes,
            navHostController = navHostController
        )
    }

}

@Preview
@Composable
private fun PreviewHomeScreen() {
    val navHostController = rememberNavController()

    HomeScreen(navHostController = navHostController)
}