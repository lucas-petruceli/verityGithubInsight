package com.example.home.data.repository

import com.example.database.datasource.UserLocalDataSource
import com.example.home.data.User
import com.example.home.data.toEqualLocalDataBase
import com.example.home.data.toListUser
import com.example.home.data.toUserEntity
import com.example.home.data.toUsers
import com.example.net.datasource.UserRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : HomeRepository {
    override suspend fun fetchUsers(): Flow<Result<List<User>>> = flow {
        val usersLocal = userLocalDataSource.getAll()
        usersLocal?.let {
            emit(Result.success(it.toUsers()))
        }

        try {
            val userResponse = userRemoteDataSource.fetchUsers()
            if (!userResponse.toEqualLocalDataBase(usersLocal)) {
                val users = userResponse.toListUser()
                emit(Result.success(users))

                try {
                    users.forEach { userLocalDataSource.insertUser(it.toUserEntity()) }
                } catch (_: Exception) { }
            }

        } catch (e: Exception) {
            if (usersLocal.isNullOrEmpty())
                emit(Result.failure(e))
        }
    }
}