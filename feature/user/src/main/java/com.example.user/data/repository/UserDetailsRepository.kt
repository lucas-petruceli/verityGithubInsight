package com.example.user.data.repository

import com.example.user.data.ResultCustom
import kotlinx.coroutines.flow.Flow

interface UserDetailsRepository {
    suspend fun fetchUserInfosDetails(username: String) : Flow<ResultCustom>
}