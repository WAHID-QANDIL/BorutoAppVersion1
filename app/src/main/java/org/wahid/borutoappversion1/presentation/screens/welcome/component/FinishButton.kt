package org.wahid.borutoappversion1.presentation.screens.welcome.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import org.wahid.borutoappversion1.R
import org.wahid.borutoappversion1.ui.theme.Purple400

@Composable
fun FinishButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onClick: () -> Unit,
) {

    AnimatedVisibility(visible = pagerState.currentPage == 2) {

        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth(fraction = .7f)
                .statusBarsPadding(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple400 ,
                contentColor = Color.White
            )

        ) {
            Text(text = stringResource(R.string.finish))
        }
    }


}