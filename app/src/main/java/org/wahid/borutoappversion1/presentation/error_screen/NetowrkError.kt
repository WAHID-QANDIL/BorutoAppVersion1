package org.wahid.borutoappversion1.presentation.error_screen

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import org.wahid.borutoappversion1.R
import org.wahid.borutoappversion1.ui.theme.ERROR_ICON_SIZE
import org.wahid.borutoappversion1.ui.theme.SMALL_PADDING
import java.net.SocketTimeoutException

@Composable
fun NetworkError(
    error: LoadState.Error,
) {

    val message by remember {
        val message = parseErrorMessage(errorMessage = error.toString())
        mutableStateOf(message)
    }

    val icon by remember {
        mutableIntStateOf(R.drawable.ic_netowrk_error)
    }


    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 0.5f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            modifier = Modifier
                .size(ERROR_ICON_SIZE)
                .alpha(alpha = alphaAnim),
            painter = painterResource(id = icon),
            contentDescription = "Error Icon",
            tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
        )
        Text(
            modifier = Modifier
                .padding(top = SMALL_PADDING)
                .alpha(alpha = alphaAnim),
            text = message,
            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )

    }


}

fun parseErrorMessage(errorMessage: String): String {

    return when {
        errorMessage.contains("java.net.SocketTimeoutException") -> "Server Unavailable"
        errorMessage.contains("ConnectException") -> "Internet Unavailable"
        else -> "Unknown Exception"
    }

}

@Preview
@Composable
private fun NetworkErrorPreview() {
    NetworkError(LoadState.Error(SocketTimeoutException()))
}