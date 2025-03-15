package org.wahid.borutoappversion1.domain.model

import androidx.annotation.DrawableRes
import org.wahid.borutoappversion1.R

sealed class OnBoarding(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
) {

    data object First:OnBoarding(
        image = R.drawable.greetings,
        title = "Greetings",
        description = "Are you a Boruto fan? Because if you are then we have a great news for you!"
    )
    data object Second:OnBoarding(
        image = R.drawable.explore,
        title = "Explore",
        description = "Find your favorite heroes and learn some of the things that you didn't know about."
    )
    data object Third:OnBoarding(
        image = R.drawable.power,
        title = "Power",
        description = "Check out your hero's power and  see how much are they strong comparing to others."
    )

}