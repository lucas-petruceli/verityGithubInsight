package com.example.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState?>(null)
    val uiState: StateFlow<UiState?> = _uiState
    fun fetchUsers() {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                repository.fetchUsers().collect {
                    it.onSuccess { _uiState.value = UiState.Sucess(it) }
                    it.onFailure { e -> throw e }
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Network request failure")
            }
        }
    }
}