package org.wahid.borutoappversion1.presentation.screens.home.component

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import org.wahid.borutoappversion1.R
import org.wahid.borutoappversion1.ui.theme.StarColor

@Composable
fun RatingWidget(
    modifier: Modifier = Modifier,
    rating: Float,
) {

    val startPathString = stringResource(R.string.STAR_PATH)
    val starPath = remember {
        PathParser().parsePathString(pathData = startPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }
    StarWidget(
        starPath = starPath,
        starPathBounds = starPathBounds,
        clipped = false,
        empty = true
    )


}


@Composable
fun StarWidget(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float = 2f,
    clipped: Boolean = false,
    empty: Boolean = false
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

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FieldStarPreview() {
    RatingWidget(modifier = Modifier, rating = 1f)
}