package org.wahid.borutoappversion1.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.wahid.borutoappversion1.domain.use_cases.UseCases
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(private val useCases: UseCases):ViewModel() {

    fun saveOnBoardingState(isCompleted:Boolean)
    {
        viewModelScope.launch {
            useCases.saveOnBoardingUseCase(isCompleted = isCompleted)
        }
    }



}