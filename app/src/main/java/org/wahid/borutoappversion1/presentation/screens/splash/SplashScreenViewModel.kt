package org.wahid.borutoappversion1.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.wahid.borutoappversion1.domain.use_cases.UseCases
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val useCases: UseCases
):ViewModel() {


    val _onBoardingState = MutableStateFlow<Boolean>(value = false)
    val onBoardingState :StateFlow<Boolean> = _onBoardingState


    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.readOnBoardingState().collect{
                state ->
                _onBoardingState.value = state
//                Log.d("_onBoardingState", "$state")
            }

//           _onBoardingState.value = useCases.readOnBoardingState().stateIn(viewModelScope).value

        }


    }




}