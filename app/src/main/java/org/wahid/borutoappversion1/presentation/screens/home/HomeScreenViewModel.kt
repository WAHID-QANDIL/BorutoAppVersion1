package org.wahid.borutoappversion1.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.domain.use_cases.UseCases
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    useCases: UseCases,
) : ViewModel() {

    lateinit var heroes: Flow<PagingData<Hero>>
    init {
        viewModelScope.launch {
             heroes = useCases.getAllHeroesUseCase()
        }
    }




}