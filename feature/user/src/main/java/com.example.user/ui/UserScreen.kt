package com.example.user.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.UiState
import com.example.components.ShimmerListComponents
import com.example.user.ui.components.GithubRepoListComponent
import com.example.user.ui.components.ShimmerUserInfosComponent
import com.example.user.ui.components.UserInfosComponent

@Composable
fun UserDetailsScreen(
    username: String,
    onTitleChanged: (String) -> Unit,
    onCanNavigateBackChanged: (Boolean) -> Unit,
    viewModel: UserViewModel
) {
    val userState by viewModel.userUiState.collectAsState()
    val reposState by viewModel.reposUiState.collectAsState()
    val errorUserinfo = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        onTitleChanged("User Details")
        onCanNavigateBackChanged(true)
        viewModel.fetchUserInfoDetails(username)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        userState?.let { state ->
            when (state) {
                is UiState.Loading -> ShimmerUserInfosComponent()
                is UiState.Sucess -> UserInfosComponent(state.data)
                is UiState.Error -> {
                    errorUserinfo.value = true
                    ErroLoadUserInfo() {
                        viewModel.fetchUserInfoDetails(username)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        reposState?.let { state ->
            when (state) {
                is UiState.Loading -> ShimmerListComponents()
                is UiState.Sucess -> GithubRepoListComponent(state.data.githubRepos)
                is UiState.Error -> Box { }
            }
        }
    }
}

@Composable
fun ErroLoadUserInfo(dataReload: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { dataReload.invoke() }) {
            Text(text = "Reload")
        }
    }
}