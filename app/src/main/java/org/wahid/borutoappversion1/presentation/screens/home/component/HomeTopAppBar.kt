package org.wahid.borutoappversion1.presentation.screens.home.component

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.wahid.borutoappversion1.R
import org.wahid.borutoappversion1.ui.theme.titleColor
import org.wahid.borutoappversion1.ui.theme.topAppBarContainerColor
import org.wahid.borutoappversion1.ui.theme.topAppBarTitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    onSearchClick: () -> Unit,
) {


    TopAppBar(
        colors =
        TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.topAppBarContainerColor,
            titleContentColor = MaterialTheme.colorScheme.topAppBarTitleColor,
            scrolledContainerColor = MaterialTheme.colorScheme.topAppBarTitleColor,
            navigationIconContentColor = MaterialTheme.colorScheme.topAppBarTitleColor,
            actionIconContentColor = MaterialTheme.colorScheme.topAppBarTitleColor,
        ),
        title = {
            Text(
                text = stringResource(R.string.explore),
                color = MaterialTheme.colorScheme.topAppBarTitleColor
            )
        },
        actions = {

            IconButton(
                onClick = onSearchClick
            ) {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon)
                )

            }

        }


    )

}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeTopAppBar() {
    HomeTopAppBar({})
}