package org.wahid.borutoappversion1.presentation.common

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.paging.compose.LazyPagingItems
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
import org.wahid.borutoappversion1.ui.theme.ROUNDED_CORNER_SIZE
import org.wahid.borutoappversion1.ui.theme.SMALL_PADDING
import org.wahid.borutoappversion1.utils.Constants.BASE_URL

@Composable
fun ContentList(
    modifier: Modifier = Modifier,
    heroes: LazyPagingItems<Hero>,
    navHostController: NavHostController,

    ) {
    Log.d("ContentList", "ContentList: ${heroes.loadState}")

    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
    ) {

        items(
            count = heroes.itemCount,
        ) { heroIndex ->
            val hero = heroes[heroIndex]
            hero?.let {
                HeroItem(
                    hero = heroes.toHero(heroIndex),
                    navHostController = navHostController
                )
            }

        }
        Log.d("Heroes", "ContentList: ${heroes.itemCount}")
    }


}


fun LazyPagingItems<Hero>.toHero(index: Int): Hero {
    val heroItem = this[index] ?: Hero(
        id = 0,
        name = "",
        rating = 0.0,
        about = "",
        day = "",
        image = "",
        month = "",
        power = 0,
        family = emptyList(),
        abilities = emptyList(),
        natureTypes = emptyList()
    )

    return Hero(
        id = heroItem.id,
        name = heroItem.name,
        rating = heroItem.rating,
        day = heroItem.day,
        image = heroItem.image,
        month = heroItem.month,
        family = heroItem.family,
        about = heroItem.about,
        abilities = heroItem.abilities,
        natureTypes = heroItem.natureTypes,
        power = heroItem.power
    )
}


@Composable
fun HeroItem(
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
            shape = RoundedCornerShape(ROUNDED_CORNER_SIZE)
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
                bottomStart = ROUNDED_CORNER_SIZE,
                bottomEnd = ROUNDED_CORNER_SIZE,
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
    HeroItem(
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