package org.wahid.borutoappversion1.presentation.common

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.placeholder
import org.wahid.borutoappversion1.R
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.navigation.Screen
import org.wahid.borutoappversion1.presentation.screens.home.component.RatingWidget
import org.wahid.borutoappversion1.ui.theme.HERO_HOME_ITEM_HEIGHT
import org.wahid.borutoappversion1.ui.theme.MEDIUM_PADDING
import org.wahid.borutoappversion1.ui.theme.DEFAULT_ROUNDED_CORNER_SIZE
import org.wahid.borutoappversion1.utils.Constants.BASE_URL

@Composable
fun HeroHomeItem(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    hero: Hero,
) {
    val heroImageUrl = "$BASE_URL${hero.image}"
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(
            LocalContext.current
        ).data(
            heroImageUrl
        ).placeholder(
            R.drawable.place_holder
        ).error(R.drawable.place_holder).build()
    )
    Box(
        modifier = modifier
            .height(HERO_HOME_ITEM_HEIGHT)
            .clickable {
                navHostController.navigate(Screen.Details.passHeroId(heroId = hero.id))
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            shape = RoundedCornerShape(DEFAULT_ROUNDED_CORNER_SIZE)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painter,
                    contentDescription = stringResource(R.string.hero_image),
                    contentScale = ContentScale.Crop,
                )
                Log.d("imageURL", "HeroItem: $heroImageUrl")
            }

        }

        Surface(
            modifier = Modifier
                .fillMaxHeight(fraction = 0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = .5f),
            shape = RoundedCornerShape(
                bottomStart = DEFAULT_ROUNDED_CORNER_SIZE,
                bottomEnd = DEFAULT_ROUNDED_CORNER_SIZE,
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MEDIUM_PADDING),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = hero.name,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.about,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = Color.White.copy(alpha = 0.6f),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    RatingWidget(
                        rating = hero.rating
                    )
                    Text(text = "(${hero.rating})")


                }


            }


        }

    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HeroItemPreview() {
    val navHostController = rememberNavController()
    HeroHomeItem(
        navHostController = navHostController,
        hero = Hero(
            id = 1,
            name = "Wahid",
            rating = 3.5,
            about = "This is the hero and this text is for demonstration purposes only",
            day = "",
            image = "",
            month = "",
            power = 3,
            family = emptyList(),
            abilities = emptyList(),
            natureTypes = emptyList()
        )

    )

}