package org.wahid.borutoappversion1.presentation.common

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import org.wahid.borutoappversion1.domain.mapper.toHero
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.presentation.place_holder.PlaceHolderScreen
import org.wahid.borutoappversion1.presentation.screens.home.component.ShimmerEffect
import org.wahid.borutoappversion1.ui.theme.SMALL_PADDING

@Composable
fun ContentList(
    modifier: Modifier = Modifier,
    heroes: LazyPagingItems<Hero>,
    navHostController: NavHostController,
    isShowing: Boolean = false

    ) {
    Log.d("ContentList", "ContentList: ${heroes.loadState}")

    val listState = rememberLazyListState()
    val pagingResult = handelPagingResult(heroes = heroes, isShowing = isShowing)


    if (pagingResult) {
        LazyColumn(
            state = listState,
            modifier = modifier
                .fillMaxWidth(),
//                .padding(horizontal = SMALL_PADDING, vertical = SMALL_PADDING),
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
        ) {

            items(
                count = heroes.itemCount,
            ) { heroIndex ->
                val hero = heroes[heroIndex]
                hero?.let {
                    HeroHomeItem(
                        hero = heroes.toHero(heroIndex),
                        navHostController = navHostController
                    )
                }

            }
            Log.d("Heroes", "ContentList: ${heroes.itemCount}")
        }
    }


}

@Composable
fun handelPagingResult(
    heroes: LazyPagingItems<Hero>,
    isShowing: Boolean = false,
): Boolean {

    with(heroes) {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return if (isShowing) {
            when {

                error != null -> {
                    Log.d("handelPagingResult", "handelPagingResult: $error")
                    PlaceHolderScreen(error, heroes = heroes)
                    false
                }

                loadState.refresh is LoadState.Loading -> {
                    ShimmerEffect()
                    false
                }

                heroes.itemCount < 1 -> {
                    PlaceHolderScreen()
                    false
                }

                else -> true
            }

        } else {
            false
        }

    }

}