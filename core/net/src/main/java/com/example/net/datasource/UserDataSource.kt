package com.example.net.datasource

import com.example.net.ApiService
import com.example.net.model.UserDetailsResponse
import com.example.net.model.UserResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class UserDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchUsers(): List<UserResponse> {
        return apiService.fetchUsers()
    }

    suspend fun fetchUserDetails(username: String): UserDetailsResponse {
        val a =  apiService.fetchUserDetails(username)
        return a;
    }

    fun createMockApi(): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}