package org.wahid.borutoappversion1.presentation.screens.home.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.wahid.borutoappversion1.ui.theme.SMALL_PADDING

@Composable
fun ShimmerEffect() {

    LazyColumn(
        modifier= Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = SMALL_PADDING)
    ) {
        items (2){
            AnimatedHeroShimmerItem()
        }

    }
}