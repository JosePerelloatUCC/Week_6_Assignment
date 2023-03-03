package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SimpleAppUiState(
    val temp:Int = 0
)

class SimpleAppViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SimpleAppUiState())
    val uiState: StateFlow<SimpleAppUiState> = _uiState.asStateFlow()
}

