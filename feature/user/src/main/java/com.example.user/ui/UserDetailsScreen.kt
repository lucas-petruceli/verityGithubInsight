package com.example.user.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun UserDetailsScreen(
    userId: Int,
    onTitleChanged: (String) -> Unit,
    onCanNavigateBackChanged: (Boolean) -> Unit, ) {
    val viewModel: UserDetailsViewModel = hiltViewModel()
    val userState by viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        onTitleChanged("User Details")
        onCanNavigateBackChanged(true)
    }

    Log.i("Lucasteste", "onCreate: Desenhando UserDetailsScreen")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { viewModel.fetchUserDetails("mojombo") }) {
            Text(text = "Load User")
        }

        userState?.let {
            Image(
                painter = rememberAsyncImagePainter(model = it.avatarUrl),
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = "User: ${it.name}")
        }
    }
}