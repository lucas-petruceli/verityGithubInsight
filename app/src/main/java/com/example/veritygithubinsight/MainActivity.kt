package com.example.veritygithubinsight

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.home.ui.HomeScreen
import com.example.user.ui.UserDetailsScreen
import com.example.veritygithubinsight.ui.theme.VerityGitHubInsightTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            VerityGitHubInsightTheme {
                val navController = rememberNavController()
                var topBarTitle by rememberSaveable { mutableStateOf("Users") }
                var canNavigateBack by rememberSaveable { mutableStateOf(false) }

                Log.i("Lucasteste", "onCreate: Desenhando MainActivity")

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    topBar = {
                        CenterAlignedTopAppBar(modifier = Modifier.fillMaxWidth(),
                            navigationIcon = {
                                if (canNavigateBack) {
                                    IconButton(onClick = {
                                        Log.i("Lucasteste", "click voltar")
                                        navController.popBackStack()
                                    }) {
                                        Icon(
                                            Icons.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                }
                            },
                            title = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = topBarTitle,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        )
                    }

                ) { innerPadding ->
                    MyNavhost(
                        innerPadding = innerPadding,
                        navController,
                        onTitleChanged = { topBarTitle = it },
                        onCanNavigateBackChanged = { canNavigateBack = it })
                }
            }
        }
    }
}


@Composable
fun MyNavhost(
    innerPadding: PaddingValues,
    navController: NavHostController,
    onTitleChanged: (String) -> Unit,
    onCanNavigateBackChanged: (Boolean) -> Unit
) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                navController,
                onTitleChanged,
                onCanNavigateBackChanged
            )
        }
        composable("details/{userId}/{username}") {
            val userId = it.arguments?.getString("userId")?.toIntOrNull()
            val username = it.arguments?.getString("username")
            userId?.let {id ->
                username?.let { name ->
                    UserDetailsScreen(
                        id,
                        name,
                        onTitleChanged,
                        onCanNavigateBackChanged
                    )
                }
            }

        }
    }
}