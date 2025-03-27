package org.wahid.borutoappversion1.presentation.screens.home.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AnimatedHeroShimmerItem()
{
    val transition = rememberInfiniteTransition()

    val alphaAnimation by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )


    HeroShimmerItem(alphaAnimation)
}

@Preview
@Composable
private fun AnimatedHeroShimmerItemPreview() {
    AnimatedHeroShimmerItem()
}