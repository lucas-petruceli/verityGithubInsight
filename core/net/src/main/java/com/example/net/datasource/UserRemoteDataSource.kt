package com.example.net.datasource

import com.example.net.ApiService
import com.example.net.model.UserDetailsResponse
import com.example.net.model.UserResponse
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchUsers(): List<UserResponse> {
        return apiService.fetchUsers()
    }

    suspend fun fetchUserDetails(username: String): UserDetailsResponse {
        return apiService.fetchUserDetails(username)
    }
}