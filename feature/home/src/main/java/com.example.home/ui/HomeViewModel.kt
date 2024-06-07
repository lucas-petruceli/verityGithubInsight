package com.example.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.datasource.UserLocalDataSource
import com.example.home.data.User
import com.example.home.data.toListUser
import com.example.home.data.toUsers
import com.example.net.datasource.UserDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userDataSource: UserDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : ViewModel() {

    init {
        fetchUsers()
    }

    private val _user = MutableStateFlow<List<User>?>(null)
    val user: StateFlow<List<User>?> = _user

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
//                val userEntities = userLocalDataSource.getAll()
//
//                userEntities?.let {
//                    _user.value = it.toUsers()
//                }

                val userResponse = userDataSource.fetchUsers()
                val users = userResponse.toListUser()
                _user.value = users

//                users.forEach {
//                    userLocalDataSource.insertUser(it.toUserEntity())
//                }

            }catch (e: Exception){
                Log.i("Lucasteste", "exception")
            }
        }
    }
}