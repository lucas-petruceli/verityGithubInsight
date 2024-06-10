package com.example.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home.data.User
import com.example.home.ui.compontents.SearchComponent
import androidx.navigation.NavHostController
import com.example.common.UiState
import com.example.components.AvatarUserComponent
import com.example.components.ShimmerListComponents

@Composable
fun HomeScreen(
    navController: NavHostController,
    onTitleChanged: (String) -> Unit,
    onCanNavigateBackChanged: (Boolean) -> Unit,
    viewModel: HomeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val isLoadData = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        onTitleChanged("Home")
        onCanNavigateBackChanged(false)
    }

    if (!isLoadData.value) {
        LaunchedEffect(Unit) {
            viewModel.fetchUsers()
            isLoadData.value = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        SearchComponent(searchQuery = searchQuery) { searchQuery = it }

        uiState?.let {
            when (it) {
                is UiState.Loading -> ShimmerListComponents()
                is UiState.Error -> ErrorComponent(message = it.message)
                is UiState.Sucess -> SuccesComponent(
                    users = it.data,
                    searchText = searchQuery.text,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun ErrorComponent(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, textAlign = TextAlign.Center)
    }
}

@Composable
fun SuccesComponent(users: List<User>, searchText: String, navController: NavHostController) {
    val filterUser = users.filter {
        it.name.contains(searchText, ignoreCase = true)
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filterUser) { user ->
            ItemCard(user) { item ->
                navController.navigate("user/${item.name}")
            }
        }
    }
}

@Composable
fun ItemCard(user: User, onClick: (User) -> Unit) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable { onClick(user) },
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            AvatarUserComponent(imageUrl = user.avatarUrl, width = 100.dp, height = 90.dp)
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = user.name,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}