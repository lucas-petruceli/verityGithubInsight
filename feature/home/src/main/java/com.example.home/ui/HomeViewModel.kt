package com.example.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.data.User
import com.example.home.data.toListUser
import com.example.net.datasource.UserDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userDataSource: UserDataSource
) : ViewModel() {

    init {
        fetchUsers(null)
    }

    private val _user = MutableStateFlow<List<User>?>(null)
    val user: StateFlow<List<User>?> = _user

    fun fetchUsers(username: String?) {
        viewModelScope.launch {
            try {
                val userResponse = userDataSource.fetchUsers()
                _user.value = userResponse.toListUser()
            }catch (e: Exception){
                Log.i("Lucasteste", "exception")
            }
        }
    }
}