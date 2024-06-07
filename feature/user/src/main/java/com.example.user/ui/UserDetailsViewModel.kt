package com.example.user.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.datasource.UserLocalDataSource
import com.example.database.entity.UserEntity
import com.example.net.datasource.GithubRepoDataSource
import com.example.net.datasource.UserDataSource
import com.example.user.data.GithubRepo
import com.example.user.data.UserDetails
import com.example.user.data.toListGithubRepo
import com.example.user.data.toUserDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userRemoteDataSource: UserDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val githubRepoDataSource: GithubRepoDataSource
) : ViewModel() {

    private val _user = MutableStateFlow<UserDetails?>(null)
    val user: StateFlow<UserDetails?> = _user

    private val _githubRepos = MutableStateFlow<List<GithubRepo>?>(null)
    val githubRepo: StateFlow<List<GithubRepo>?> = _githubRepos

//    init {
//        fetchLocalUser(1)
//        fetchGithubRepos("mojombo")
//    }

    fun fetchUserInfos(userId: Int, username: String) {
//        fetchLocalUser(userId)
//        fetchGithubRepos(username)
        viewModelScope.launch {
            joinAll(
                async { fetchLocalUser(userId) },
                async { fetchGithubRepos(username) }
            )
        }
    }

    fun fetchUserDetails(username: String) {
        viewModelScope.launch {
            try {
                val userResponse = userRemoteDataSource.fetchUserDetails(username)
                _user.value = userResponse.toUserDetails()
            } catch (e: Exception) {
                Log.i("Lucasteste", "exception")
            }
        }
    }

    suspend fun fetchLocalUser(userId: Int) {
        Log.i("Lucasteste", "fetchLocalUser: $userId")
//        viewModelScope.launch {
//            delay(2000)
//            val userEntity = userLocalDataSource.getUserById(userId)
//            userEntity?.let {
//                _user.value = userEntity.toUserDetails()
//            }
//        }
//        delay(2000)
        val userEntity = userLocalDataSource.getUserById(userId)
        userEntity?.let {
            _user.value = userEntity.toUserDetails()
        }
    }

    suspend fun fetchGithubRepos(username: String) {
        Log.i("Lucasteste", "fetchGithubRepos: $username")
//        viewModelScope.launch {
//            val repos = githubRepoDataSource.fetchGithubRepo(username)
//            _githubRepos.value = repos.toListGithubRepo()
//        }
        val repos = githubRepoDataSource.fetchGithubRepo(username)
        _githubRepos.value = repos.toListGithubRepo()
    }
}

