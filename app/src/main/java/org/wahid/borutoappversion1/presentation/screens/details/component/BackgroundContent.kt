package org.wahid.borutoappversion1.presentation.screens.details.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.placeholder
import org.wahid.borutoappversion1.R
import org.wahid.borutoappversion1.ui.theme.INFO_BOX_ICON_SIZE
import org.wahid.borutoappversion1.ui.theme.MEDIUM_PADDING
import org.wahid.borutoappversion1.ui.theme.SMALL_PADDING
import org.wahid.borutoappversion1.utils.Constants.BASE_URL
import org.wahid.borutoappversion1.utils.Constants.MIN_BACKGROUND_SIZE

@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onCloseClicked: () -> Unit,
) {

    val imageUrl = "$BASE_URL$heroImage"
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(
            LocalContext.current
        ).data(
            imageUrl
        ).placeholder(
            R.drawable.place_holder
        ).error(R.drawable.place_holder).build()
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),

        ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = if (imageFraction < 1) MIN_BACKGROUND_SIZE else imageFraction ),
            painter = painter,
            contentDescription = stringResource(R.string.hero_image),
            contentScale = ContentScale.Crop,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier
                    .padding(all = SMALL_PADDING)
                    .size(INFO_BOX_ICON_SIZE)
                ,
                onClick = { onCloseClicked() },
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close),
                    tint = Color.Gray
                )
            }
        }


    }

}

@Preview
@Composable
private fun BackgroundContentPreview() {
    BackgroundContent(
        "/images/naruto.jpg"
    ) {

    }
}