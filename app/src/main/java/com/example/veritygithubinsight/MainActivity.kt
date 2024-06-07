package com.example.veritygithubinsight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.home.ui.HomeScreen
import com.example.user.ui.UserDetailsScreen
import com.example.veritygithubinsight.ui.theme.VerityGitHubInsightTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VerityGitHubInsightTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                ) { innerPadding ->
                    MyNavhost(innerPadding = innerPadding)
                }
            }
        }
    }
}


@Composable
fun MyNavhost(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen() }
        composable("details") { UserDetailsScreen() }
    }
}