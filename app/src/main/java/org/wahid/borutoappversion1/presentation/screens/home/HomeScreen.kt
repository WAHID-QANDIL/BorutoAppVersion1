package org.wahid.borutoappversion1.presentation.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import org.wahid.borutoappversion1.presentation.screens.home.component.HomeTopAppBar
import org.wahid.borutoappversion1.presentation.screens.home.component.RatingWidget


@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {
    val hreoes = viewModel.heroes.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeTopAppBar({})
        }
    ) {
        val padding = it

        RatingWidget(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(horizontal = 40.dp),
            rating = 5.5f
        )
    }

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewHomeScreen() {
    val navHostController = rememberNavController()

    HomeScreen(navHostController = navHostController)
}