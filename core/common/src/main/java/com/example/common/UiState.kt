package com.example.common

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Error(val message: String = "") : UiState<Nothing>()
    data class Sucess<out T>(val data: T) : UiState<T>()
}