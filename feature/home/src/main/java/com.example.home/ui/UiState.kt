package com.example.home.ui

import com.example.home.data.User

sealed class UiState {
    data object Loading: UiState()
    data class Error(val message: String): UiState()
    data class Sucess(
        val users: List<User>
    ): UiState()
}