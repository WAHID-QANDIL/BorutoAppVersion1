package org.wahid.borutoappversion1.presentation.screens.home

import android.content.res.Configuration
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.wahid.borutoappversion1.presentation.screens.home.component.HomeTopAppBar


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    Scaffold(
        topBar = {
            HomeTopAppBar({})
        }
    ){
        val padding = it
    }


}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewHomeScreen() {
    val navHostController = rememberNavController()

    HomeScreen(navHostController = navHostController)
}