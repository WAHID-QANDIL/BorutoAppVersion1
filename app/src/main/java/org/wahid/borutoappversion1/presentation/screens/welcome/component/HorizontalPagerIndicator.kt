package org.wahid.borutoappversion1.presentation.screens.welcome.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.wahid.borutoappversion1.ui.theme.Purple40

@Composable
fun HorizontalPagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {

    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    )
    {
        repeat(pagerState.pageCount) { page ->
            val indicateColor = if (pagerState.currentPage == page) Purple40 else Color.Gray
            val indicatorWidth = if (pagerState.currentPage == page) 30.dp else 10.dp
//            val horizontalIndicatorPadding = if (pagerState.currentPage == page) 10.dp else 2.dp

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(indicateColor)
                    .border(
                        1.dp,
                        color = Purple40,
                        shape = CircleShape
                    )
                    .size(width = indicatorWidth, height = 10.dp),
                contentAlignment = Alignment.Center
//                    .padding(horizontal = padding)
            )
            {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedVisibility(
                        visible = pagerState.currentPage == page
                    )
                    {
                        Icon(
                            contentDescription = "Icon",
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            tint = Color.White,
//                            modifier = Modifier.clickable {
//                                CoroutineScope(Dispatchers.IO).launch {
//                                    pagerState.scrollToPage(page + 1)
//                                }
//                            }
                        )
                    }

                }


            }

        }


    }

}