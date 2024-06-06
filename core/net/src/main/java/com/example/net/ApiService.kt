package com.example.net

import com.example.net.model.UserDetailsResponse
import com.example.net.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{username}")
    suspend fun fetchUserDetails(@Path("username") username: String): UserDetailsResponse

    @GET("users")
    suspend fun fetchUsers(): List<UserResponse>
}