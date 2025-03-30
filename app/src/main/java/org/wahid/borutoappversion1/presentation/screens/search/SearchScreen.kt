package org.wahid.borutoappversion1.presentation.screens.search

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import org.wahid.borutoappversion1.presentation.common.ContentList
import org.wahid.borutoappversion1.presentation.screens.search.component.SearchTopAppBar
import org.wahid.borutoappversion1.ui.theme.LARGE_PADDING


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel(),
) {

    val query by searchScreenViewModel.searchQuery
    val heroes = searchScreenViewModel.searchedHeroes.collectAsLazyPagingItems()
    Log.d("Searched heroes", "SearchScreen: $heroes")
    var isShowing = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {

            SearchTopAppBar(
                modifier = Modifier.padding(top = LARGE_PADDING)
                    .height(TopAppBarDefaults.TopAppBarExpandedHeight)

                ,
                text = query,
                onSearchClicked = {
                    searchScreenViewModel.searchedHeroes(query = it)
                    isShowing.value = true
                },
                onTextChanged = {
                    searchScreenViewModel.updateSearchQuery(it)
                },
                onCloseClicked = {
                    navHostController.navigateUp()
                }
            )
        },
        content = {
            val padding = it
            ContentList(
                modifier = Modifier.padding(padding),
                heroes = heroes,
                navHostController = navHostController,
                isShowing = isShowing.value
            )
        }

    )

}