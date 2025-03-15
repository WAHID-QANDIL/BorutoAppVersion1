package org.wahid.borutoappversion1.presentation.screens.welcome
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.wahid.borutoappversion1.domain.model.OnBoarding
import org.wahid.borutoappversion1.navigation.Screen
import org.wahid.borutoappversion1.presentation.screens.welcome.component.FinishButton
import org.wahid.borutoappversion1.presentation.screens.welcome.component.HorizontalPagerIndicator
import org.wahid.borutoappversion1.presentation.screens.welcome.component.WelcomeScreenPageItem
import org.wahid.borutoappversion1.ui.theme.welcomeScreenBackgroundColor

@Composable
fun WelcomeScreen(
    navHostController: NavHostController,
    viewModel: WelcomeScreenViewModel = hiltViewModel()
) {

    val pages = listOf(
        OnBoarding.First,
        OnBoarding.Second,
        OnBoarding.Third
    )

    val pagerState = rememberPagerState(
        pageCount = { pages.size }
    )
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.welcomeScreenBackgroundColor),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            HorizontalPager(pagerState) { pageNumber: Int ->
                WelcomeScreenPageItem(pages[pageNumber])
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                HorizontalPagerIndicator(pagerState = pagerState)
                FinishButton(
                    pagerState = pagerState,
                ) {
                    navHostController.popBackStack()
                    viewModel.saveOnBoardingState(isCompleted = true)
                    navHostController.navigate(Screen.Home.route)
                }
            }


        }


    }


}

@Preview(name = "Welcome", showBackground = true)
@Composable
private fun PreviewWelcome() {
    val nav = rememberNavController()
    WelcomeScreen(navHostController = nav)
}