package org.wahid.borutoappversion1.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class SearchScreenViewModel : ViewModel(){

    private var _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    fun updateSearchQuery(searchQuery:String){
        _searchQuery.value = searchQuery
    }

}