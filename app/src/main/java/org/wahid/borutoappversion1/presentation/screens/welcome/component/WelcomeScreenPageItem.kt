package org.wahid.borutoappversion1.presentation.screens.welcome.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.wahid.borutoappversion1.domain.model.OnBoarding
import org.wahid.borutoappversion1.ui.theme.titleColor

@Composable
fun WelcomeScreenPageItem(
    onBoarding: OnBoarding,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painter = painterResource(onBoarding.image),
            contentDescription = onBoarding.title,
            modifier = Modifier
                .fillMaxHeight(fraction = .7f)
                .fillMaxWidth(fraction = .5f)
        )
        Text(
            modifier = Modifier.fillMaxWidth(fraction = .7f),
            text = onBoarding.title,
            style = TextStyle(
                color = MaterialTheme.colorScheme.titleColor,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
            )
        )
        Text(
            modifier = Modifier
                .padding(16.dp)
            ,
            text = onBoarding.description,
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
            )
        )


    }

}

@Preview(name = "WelcomeScreenPageItem")
@Composable
private fun PreviewWelcomeScreenPageItem() {
    WelcomeScreenPageItem(OnBoarding.First)
}