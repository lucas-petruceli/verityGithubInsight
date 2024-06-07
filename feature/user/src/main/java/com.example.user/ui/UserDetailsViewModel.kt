package com.example.user.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.net.datasource.UserDataSource
import com.example.user.data.UserDetails
import com.example.user.data.toUserDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userDataSource: UserDataSource
) : ViewModel() {

    init {
        Log.i("Lucasteste", "viewModel")
    }


    private val _user = MutableStateFlow<UserDetails?>(null)
    val user: StateFlow<UserDetails?> = _user

    fun fetchUserDetails(username: String) {
        viewModelScope.launch {
            try {
                val userResponse = userDataSource.fetchUserDetails(username)
                _user.value = userResponse.toUserDetails()
            }catch (e: Exception){
                Log.i("Lucasteste", "exception")
            }
        }
    }
}

