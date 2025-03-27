package org.wahid.borutoappversion1.domain.mapper

import androidx.paging.compose.LazyPagingItems
import org.wahid.borutoappversion1.domain.model.Hero

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