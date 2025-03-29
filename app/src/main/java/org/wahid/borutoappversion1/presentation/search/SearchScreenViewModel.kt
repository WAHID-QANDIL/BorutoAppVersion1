package org.wahid.borutoappversion1.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.domain.use_cases.UseCases
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private var _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedheroes = mutableStateOf<Flow<PagingData<Hero>>>(emptyFlow())
    val searchedHeroes by _searchedheroes


    fun updateSearchQuery(searchQuery: String) {
        _searchQuery.value = searchQuery
    }

    fun searchedHeroes(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchHeroes(query = query).cachedIn(viewModelScope).collect {
                _searchedheroes.value = flowOf (it)
            }
        }

    }

}