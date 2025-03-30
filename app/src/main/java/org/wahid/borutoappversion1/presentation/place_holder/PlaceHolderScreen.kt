package org.wahid.borutoappversion1.presentation.place_holder

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.paging.compose.LazyPagingItems
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import org.wahid.borutoappversion1.R
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.domain.use_cases.search_heroes.SearchHeroes
import org.wahid.borutoappversion1.ui.theme.ERROR_ICON_SIZE
import org.wahid.borutoappversion1.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun PlaceHolderScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null,
) {

    var message by remember {
        val message = "Find Your Favorite Heroes"
        mutableStateOf(message)
    }

    var icon by remember {
        mutableIntStateOf(R.drawable.ic_search_place_holder)
    }

    if (error != null) {
        message = parseErrorMessage(error)
        icon = R.drawable.ic_netowrk_error
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

    var refresh by remember { mutableStateOf(false) }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refresh),
        onRefresh = {
            refresh = true
            heroes?.refresh()
            refresh = false
        },
        swipeEnabled = error != null,
        modifier = Modifier.statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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


}

fun parseErrorMessage(loadStateError: LoadState.Error): String {

    return when (loadStateError.error) {
        is SocketTimeoutException -> "Server Unavailable"
        is ConnectException -> "Internet Unavailable"
        else -> "Unknown Exception"
    }

}

@Preview
@Composable
private fun NetworkErrorPreview() {
    PlaceHolderScreen(LoadState.Error(SocketTimeoutException()))
}