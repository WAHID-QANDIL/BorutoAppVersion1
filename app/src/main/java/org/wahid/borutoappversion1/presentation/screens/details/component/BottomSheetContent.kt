package org.wahid.borutoappversion1.presentation.screens.details.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import org.wahid.borutoappversion1.R
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.ui.theme.INFO_BOX_ICON_SIZE
import org.wahid.borutoappversion1.ui.theme.LARGE_PADDING
import org.wahid.borutoappversion1.ui.theme.MEDIUM_PADDING
import org.wahid.borutoappversion1.ui.theme.titleColor
import org.wahid.borutoappversion1.utils.Constants.ABOUT_MAX_LINE_NUMBER
import org.wahid.borutoappversion1.utils.Constants.MEDIUM_ALPHA

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxColor: Color = MaterialTheme.colorScheme.primary,
    sheetBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.titleColor,
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(MEDIUM_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_BOX_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(R.drawable.logo),
                contentDescription = stringResource(R.string.app_logo),
                tint = contentColor
            )

            Text(
                modifier = Modifier.weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Bold,
            )


        }
        Spacer(modifier = Modifier.height(LARGE_PADDING))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(R.drawable.ic_bolt),
                iconColor = infoBoxColor,
                largeText = "${selectedHero.power}",
                smaleText = stringResource(R.string.power),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(R.drawable.ic_calender),
                iconColor = infoBoxColor,
                largeText = selectedHero.month,
                smaleText = stringResource(R.string.month),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(R.drawable.ic_cake),
                iconColor = infoBoxColor,
                largeText = selectedHero.day,
                smaleText = stringResource(R.string.birthday),
                textColor = contentColor
            )
        }
        Spacer(modifier = Modifier.height(LARGE_PADDING))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.alpha(MEDIUM_ALPHA),
            text = selectedHero.about,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            maxLines = ABOUT_MAX_LINE_NUMBER,
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = stringResource(R.string.family),
                items = selectedHero.family,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.naturetypes),
                items = selectedHero.natureTypes,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.abilities),
                items = selectedHero.abilities,
                textColor = contentColor
            )
        }


    }


}


@Preview
@Composable
private fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedHero = Hero(
            id = 13,
            name = "Code",
            image = "/images/code.jpg",
            about = "Code (コード, Kōdo) is the last active Inner from Kara. Carrying Isshiki Ōtsutsuki's legacy within him, he inherits the Ōtsutsuki Clan's will to become a Celestial Being and continually evolve. At the time Kawaki was brought to Kara, Code was one of fifteen candidates in Jigen and Amado's Ōtsutsuki ritual to screen for a Kāma vessel for Isshiki. Only Kawaki survived to become an actual vessel.",
            rating = 4.8,
            power = 99,
            month = "Jan",
            day = "1st",
            family = listOf(
                "Unknown"
            ),
            abilities = listOf(
                "White Karma",
                "Transformation",
                "Genjutsu"
            ),
            natureTypes = listOf(
                "Unknown"
            )
        ),
    )
}