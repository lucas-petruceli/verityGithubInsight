package com.example.home.data.repository

import com.example.home.data.User
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun fetchUsers(): Flow<Result<List<User>>>
}