package com.example.user.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.user.ui.components.RepoItemComponent

@Composable
fun UserDetailsScreen(
    userId: Int,
    username: String,
    onTitleChanged: (String) -> Unit,
    onCanNavigateBackChanged: (Boolean) -> Unit
) {
    val viewModel: UserDetailsViewModel = hiltViewModel()
    val userState by viewModel.user.collectAsState()
    val reposState by viewModel.githubRepo.collectAsState()

    LaunchedEffect(Unit) {
        onTitleChanged("User Details")
        onCanNavigateBackChanged(true)
        viewModel.fetchUserInfos(userId, username)
    }

    Log.i("Lucasteste", "Desenhando UserDetailsScreen - userId ${userId}")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        userState?.let {
            Image(
                painter = rememberAsyncImagePainter(model = it.avatarUrl),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(200.dp)
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "User: ${it.name}",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            reposState?.let { repo ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(repo) { item ->
                        RepoItemComponent(item)
                    }
                }
            }
        }
    }
}