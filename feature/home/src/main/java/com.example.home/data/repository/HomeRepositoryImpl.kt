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
        try {
            val usersLocal = userLocalDataSource.getAll()
            usersLocal?.let {
                emit(Result.success(it.toUsers()))
            }

            val userResponse = userRemoteDataSource.fetchUsers()
            // TODO:  comparar se userlocal tem dados e se o remoto volto dado tb
            if (!userResponse.toEqualLocalDataBase(usersLocal)) {
                val users = userResponse.toListUser()
                emit(Result.success(users))
                users.forEach { userLocalDataSource.insertUser(it.toUserEntity()) }
            }

        } catch (e: Exception) {
            // TODO: comparar se userlocal tem dados, se tive nao emitir o erro
            emit(Result.failure(e))
        }
    }
}