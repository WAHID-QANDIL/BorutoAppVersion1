package org.wahid.borutoappversion1.presentation.screens.details

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.collectLatest
import org.wahid.borutoappversion1.presentation.screens.details.component.DetailsContent
import org.wahid.borutoappversion1.utils.Constants.BASE_URL
import org.wahid.borutoappversion1.utils.PaletteGenerator.extractColorsFromBitmap
import org.wahid.borutoappversion1.utils.PaletteGenerator.loadBitmapImage

@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val selectedHero by detailsScreenViewModel.selectedHero.collectAsState()
    val colorPalette by detailsScreenViewModel.colorPalette

    // Initialize colorPalette if not already done
    if (colorPalette.isNotEmpty()) {
        detailsScreenViewModel.generateColorPalette()
        Log.d("DetailsScreen", "Generating color palette")
    }

    // Display content only when we have both hero and colors
    if (selectedHero != null) {
        DetailsContent(
            navHostController = navHostController,
            selectedHero = selectedHero,
            colors = colorPalette
        )
    }

    // Handle palette generation
    LaunchedEffect(key1 = true) {
        detailsScreenViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = selectedHero?.image?.let { loadBitmapImage("$BASE_URL$it") }
                    if (bitmap != null) {
                        detailsScreenViewModel.setColorPalette(extractColorsFromBitmap(bitmap))
                    }
                }
            }
        }
    }
}