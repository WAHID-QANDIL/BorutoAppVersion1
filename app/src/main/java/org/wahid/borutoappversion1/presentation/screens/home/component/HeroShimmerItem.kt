package org.wahid.borutoappversion1.presentation.screens.home.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import org.wahid.borutoappversion1.ui.theme.HERO_HOME_ITEM_HEIGHT
import org.wahid.borutoappversion1.ui.theme.SHIMMER_STAR_PLACEHOLDER_ITEM_SIZE
import org.wahid.borutoappversion1.ui.theme.MEDIUM_PADDING
import org.wahid.borutoappversion1.ui.theme.ROUNDED_CORNER_SIZE
import org.wahid.borutoappversion1.ui.theme.SHIMMER_ABOUT_PLACEHOLDER_ITEM
import org.wahid.borutoappversion1.ui.theme.SHIMMER_NAME_PLACEHOLDER_ITEM
import org.wahid.borutoappversion1.ui.theme.SMALL_PADDING
import org.wahid.borutoappversion1.ui.theme.STARS_SPACE_BY_VALUE
import org.wahid.borutoappversion1.ui.theme.shimmerBackgroundColor
import org.wahid.borutoappversion1.ui.theme.shimmerItemColor


@Composable
fun HeroShimmerItem(alpha: Float = 1f) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(HERO_HOME_ITEM_HEIGHT),

        shape = RoundedCornerShape(ROUNDED_CORNER_SIZE),
        color = MaterialTheme.colorScheme.shimmerBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .alpha(alpha = alpha)
                    .height(SHIMMER_NAME_PLACEHOLDER_ITEM)
                    .background(
                        color = MaterialTheme.colorScheme.shimmerItemColor,
                        shape = RoundedCornerShape(SHIMMER_NAME_PLACEHOLDER_ITEM)
                    )
            ) { }
            Spacer(modifier = Modifier.height(MEDIUM_PADDING))

            repeat(3) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(alpha = alpha)
                        .height(SHIMMER_ABOUT_PLACEHOLDER_ITEM)
                        .background(
                            color = MaterialTheme.colorScheme.shimmerItemColor,
                            shape = RoundedCornerShape(SHIMMER_NAME_PLACEHOLDER_ITEM)
                        )
                ) { }
                Spacer(modifier = Modifier.height(SMALL_PADDING))
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(STARS_SPACE_BY_VALUE)
            )
            {
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .alpha(alpha = alpha)
                            .size(SHIMMER_ABOUT_PLACEHOLDER_ITEM)
                            .background(
                                color = MaterialTheme.colorScheme.shimmerItemColor,
                                shape = RoundedCornerShape(SHIMMER_STAR_PLACEHOLDER_ITEM_SIZE)
                            )
                    ) { }
                }

                Spacer(modifier = Modifier.height(SMALL_PADDING))
            }


        }


    }


}


@Preview()
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HeroShimmerItemPreview() {
    HeroShimmerItem(1f)

}