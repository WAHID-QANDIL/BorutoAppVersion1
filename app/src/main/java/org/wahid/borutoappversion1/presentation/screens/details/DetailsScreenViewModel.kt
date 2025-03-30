package org.wahid.borutoappversion1.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.domain.use_cases.UseCases
import org.wahid.borutoappversion1.utils.Constants.HERO_ID
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _selectedHero: MutableStateFlow<Hero?> = MutableStateFlow<Hero?>(null)
    val selectedHero = _selectedHero

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(HERO_ID)
            _selectedHero.value = heroId?.let { id ->
                useCases.getSelectedHero(heroId = id)
            }

        }

    }


    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(emptyMap())
    val colorPalette = _colorPalette

    fun setColorPalette(colors: Map<String, String>){
        _colorPalette.value = colors
    }


    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    fun generateColorPalette(){
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }


}

sealed class UiEvent{
    object GenerateColorPalette: UiEvent()
}