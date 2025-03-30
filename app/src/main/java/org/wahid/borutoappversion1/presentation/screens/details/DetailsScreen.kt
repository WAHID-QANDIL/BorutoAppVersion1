package org.wahid.borutoappversion1.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.wahid.borutoappversion1.presentation.screens.details.component.DetailsContent

@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val selectedHero by detailsScreenViewModel.selectedHero.collectAsState()

    // Display content only when the hero is available.
    if (selectedHero != null) {
        DetailsContent(
            navHostController = navHostController,
            selectedHero = selectedHero!!,
        )
    }
}
