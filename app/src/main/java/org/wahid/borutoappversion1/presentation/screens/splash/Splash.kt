package org.wahid.borutoappversion1.presentation.screens.splash

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import org.wahid.borutoappversion1.R
import org.wahid.borutoappversion1.ui.theme.Purple40
import org.wahid.borutoappversion1.ui.theme.PurpleGrey40
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.rotate
import androidx.hilt.navigation.compose.hiltViewModel
import org.wahid.borutoappversion1.navigation.Screen


@Composable
fun SplashScreen(
    navHostController: NavHostController,
    splashScreenViewModel: SplashScreenViewModel = hiltViewModel()
) {
    val isCompletedState by splashScreenViewModel.onBoardingState.collectAsState()

    val degrees = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200,
//                easing = Easing { fraction: Float ->
//                    fraction.times(1.5f)
//                }
            )
        )
        navHostController.popBackStack()
        if (isCompletedState){
            navHostController.navigate(Screen.Home.route)
            Log.d("SplashScreen", "SplashScreen: $isCompletedState ")
        }
        else{
            navHostController.navigate(Screen.Welcome.route)
        }
    }



    if (isSystemInDarkTheme()) {
        SplashThem(
            degrees = degrees.value,
            listOf(Color.Black, Color.Black)
        )
    } else {
        SplashThem(
            degrees = degrees.value,
            colors = listOf(
                Purple40,
                PurpleGrey40
            )
        )
    }

}

@Composable
fun SplashThem(
    degrees: Float,
    colors: List<Color>,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = colors
                )

            )

    ) {
        Image(
            modifier = Modifier.rotate(degrees = degrees),
            painter = painterResource(
                R.drawable.logo,
            ),
            contentDescription = stringResource(R.string.app_logo)
        )


    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SplashPreview(modifier: Modifier = Modifier) {
//    Splash()
}