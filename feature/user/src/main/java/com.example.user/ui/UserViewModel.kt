package com.example.user.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.UiState
import com.example.user.data.PartialUserDetails
import com.example.user.data.ResultCustom
import com.example.user.data.repository.UserDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserDetailsRepository
) : ViewModel() {
    private val _userUiState = MutableStateFlow<UiState<PartialUserDetails.UserInfo>?>(null)
    val userUiState: StateFlow<UiState<PartialUserDetails.UserInfo>?> = _userUiState

    private val _gitHubRepositoriesUiState =
        MutableStateFlow<UiState<PartialUserDetails.GithubRepositories>?>(null)
    val reposUiState: StateFlow<UiState<PartialUserDetails.GithubRepositories>?> = _gitHubRepositoriesUiState

    fun fetchUserInfoDetails(username: String) {
        _userUiState.value = UiState.Loading
        _gitHubRepositoriesUiState.value = UiState.Loading
        viewModelScope.launch {
            repository.fetchUserInfosDetails(username).collect { result ->

                when (result) {
                    is ResultCustom.Success.User -> _userUiState.value =
                        UiState.Sucess(result.data)

                    is ResultCustom.Success.GitHubRepos -> _gitHubRepositoriesUiState.value =
                        UiState.Sucess(result.data)

                    is ResultCustom.Error.User -> _userUiState.value = UiState.Error()

                    is ResultCustom.Error.GitHubRepos -> _gitHubRepositoriesUiState.value =
                        UiState.Error("Não foi possivel recuperar os repositorios do usairo")
                }
            }
        }
    }
}
