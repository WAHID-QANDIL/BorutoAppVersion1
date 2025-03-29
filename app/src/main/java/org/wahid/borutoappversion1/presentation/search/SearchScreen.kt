package org.wahid.borutoappversion1.presentation.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.wahid.borutoappversion1.presentation.search.component.SearchTopAppBar
import org.wahid.borutoappversion1.ui.theme.LARGE_PADDING


@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel(),
) {

    val query by searchScreenViewModel.searchQuery


    Scaffold(
        topBar = {
            SearchTopAppBar(
                modifier = Modifier.padding(top = LARGE_PADDING),
                text = query,
                onSearchClicked = {},
                onTextChanged = {
                    searchScreenViewModel.updateSearchQuery(it)
                },
                onCloseClicked = {
                    navHostController.navigateUp()
                }
            )
        },
    ) {
        val padding = it
    }

}