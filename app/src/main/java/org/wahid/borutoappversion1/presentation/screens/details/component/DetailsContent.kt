package org.wahid.borutoappversion1.presentation.screens.details.component

import android.util.Log
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.ui.theme.LARGE_ROUNDED_CORNER_SIZE
import org.wahid.borutoappversion1.ui.theme.SHEET_PEEK_HEIGHT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(
    navHostController: NavHostController,
    selectedHero: Hero?,
    colors: Map<String,String>,
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val imageFractionAnim by animateFloatAsState(
        targetValue = currentSheetFraction,
        animationSpec = tween(
            durationMillis = 10,
            delayMillis = 0,
            easing = EaseInOut
        )
    )

    val reduceAnim by animateDpAsState(
        targetValue = if (currentSheetFraction == 1f) LARGE_ROUNDED_CORNER_SIZE else 0.dp
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = SHEET_PEEK_HEIGHT,
        sheetContent = {
            selectedHero?.let { hero ->
                BottomSheetContent(
                    selectedHero = hero,
                    infoBoxColor = Color(colors["vibrant"]?.toColorInt()!!),
                    sheetBackgroundColor = Color(colors["darkVibrant"]?.toColorInt()!!),
                    contentColor = Color(colors["onDarkVibrant"]?.toColorInt()!!),
                )
            }
        },
        content = {
            selectedHero?.let { hero ->
                BackgroundContent(
                    heroImage = hero.image,
                    imageFraction = imageFractionAnim,
                    backgroundColor = Color(colors["darkVibrant"]?.toColorInt()!!)
                ) {
                    navHostController.navigateUp()
                }
            }
        },
        sheetShadowElevation = 4.dp,
        sheetShape = RoundedCornerShape(
            topStart = reduceAnim,
            topEnd = reduceAnim,
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val currentValue = bottomSheetState.currentValue
        val targetValue = bottomSheetState.targetValue

        Log.d("BottomSheetScaffoldState", "currentValue: $currentValue")
        Log.d("BottomSheetScaffoldState", "targetValue: $targetValue")

        return when {
            currentValue == SheetValue.Hidden && targetValue == SheetValue.Hidden -> 1f
            currentValue == SheetValue.Expanded && targetValue == SheetValue.Expanded -> 0f
            currentValue == SheetValue.Hidden && targetValue == SheetValue.Expanded -> 1f
            currentValue == SheetValue.Expanded && targetValue == SheetValue.Hidden -> 0f
            else -> 1f
        }
    }