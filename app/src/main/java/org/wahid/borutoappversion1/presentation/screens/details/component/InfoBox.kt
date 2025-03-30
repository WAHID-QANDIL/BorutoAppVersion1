package org.wahid.borutoappversion1.presentation.screens.details.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import org.wahid.borutoappversion1.R
import org.wahid.borutoappversion1.ui.theme.INFO_BOX_ICON_SIZE
import org.wahid.borutoappversion1.ui.theme.SMALL_PADDING
import org.wahid.borutoappversion1.ui.theme.titleColor
import org.wahid.borutoappversion1.utils.Constants.MEDIUM_ALPHA

@Composable
fun InfoBox(
    modifier: Modifier = Modifier,
    icon: Painter,
    textColor: Color,
    iconColor: Color,
    largeText: String,
    smaleText: String,

    ) {


    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier
                .padding(end = SMALL_PADDING)
                .size(INFO_BOX_ICON_SIZE),
            painter = icon,
            contentDescription = stringResource(R.string.info_icon),
            tint = iconColor
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.padding(end = SMALL_PADDING)
        ) {
            Text(
                text = largeText,
                color = textColor,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight.Black
            )
            Text(
                text = smaleText,
                color = textColor,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Black,
                modifier = Modifier.alpha(MEDIUM_ALPHA)
            )

        }


    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InfoBoxPreview() {
    InfoBox(
        icon = painterResource(R.drawable.ic_bolt),
        textColor = MaterialTheme.colorScheme.titleColor,
        iconColor = MaterialTheme.colorScheme.primary,
        largeText = "92",
        smaleText = "Power"
    )
}