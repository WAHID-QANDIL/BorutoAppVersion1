package org.wahid.borutoappversion1.presentation.screens.home.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.wahid.borutoappversion1.R
import org.wahid.borutoappversion1.ui.theme.STARS_SPACE_BY_VALUE
import org.wahid.borutoappversion1.ui.theme.StarColor

@Composable
fun RatingWidget(
    rating: Double,
    spaceBy: Dp = STARS_SPACE_BY_VALUE,
    scaleFactor: Float = 2f,
) {

    val stars: RatingWidgetStars = starsCals(rating = rating)

    val startPathString = stringResource(R.string.STAR_PATH)
    val starPath = remember {
        PathParser().parsePathString(pathData = startPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(spaceBy)
    )
    {
        repeat(stars.fieldStars) {
            StarWidget(
                starPath = starPath,
                starPathBounds = starPathBounds,
                scaleFactor = scaleFactor
            )
        }
        repeat(stars.halfFieldStars) {
            StarWidget(
                starPath = starPath,
                starPathBounds = starPathBounds,
                scaleFactor = scaleFactor,
                clipped = true
            )
        }
        repeat(stars.emptyStars) {
            StarWidget(
                starPath = starPath,
                starPathBounds = starPathBounds,
                scaleFactor = scaleFactor,
                empty = true
            )
        }

    }


}


@Composable
fun StarWidget(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float = 2f,
    clipped: Boolean = false,
    empty: Boolean = false,
) {

    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = this.size
        val pathWidth = starPathBounds.width
        val pathHeight = starPathBounds.height
        val left: Float = (canvasSize.width / 2) - (pathWidth / 2)
        val top: Float = (canvasSize.height / 2) - (pathHeight / 2)

        scale(scaleFactor) {
            translate(
                left = left,
                top = top
            ) {
                drawPath(
                    path = starPath,
                    color = if (clipped || empty) LightGray.copy(alpha = .5f) else StarColor
                )
                if (clipped) {
                    clipPath(path = starPath) {
                        drawRect(
                            color = StarColor,
                            size = Size(
                                width = starPathBounds.maxDimension / 2f,
                                height = starPathBounds.maxDimension * scaleFactor
                            )
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun starsCals(rating: Double): RatingWidgetStars {

    val maxStars by remember { mutableIntStateOf(5) }

    var fieldStars by remember { mutableIntStateOf(0) }
    var halfFieldStars by remember { mutableIntStateOf(0) }
    var emptyStars by remember { mutableIntStateOf(0) }

    fieldStars = rating.toInt()
    halfFieldStars = if (rating - fieldStars >= 0.5f) 1 else 0
    emptyStars = maxStars - fieldStars - halfFieldStars


    if (fieldStars.plus(halfFieldStars) > maxStars) {
        fieldStars = 0
        halfFieldStars = 0
        emptyStars = 5
    }

    return RatingWidgetStars(
        emptyStars = emptyStars,
        fieldStars = fieldStars,
        halfFieldStars = halfFieldStars
    )


}


@Preview(showBackground = true)
@Composable
private fun FieldStarPreview() {
    RatingWidget(rating = 1.6)
}