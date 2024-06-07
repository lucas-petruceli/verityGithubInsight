package com.example.database.datasource

import com.example.database.dao.UserDao
import com.example.database.entity.UserEntity
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)
    suspend fun getUserById(id: Int) = userDao.getUserById(id)
    suspend fun getAll() = userDao.getAll()
}