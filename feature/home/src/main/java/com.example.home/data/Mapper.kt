package com.example.home.data

import com.example.database.entity.UserEntity
import com.example.net.model.UserResponse

fun List<UserResponse>.toListUser(): List<User> {
    return this.map {
        User(
            it.id,
            it.login,
            it.avatarUrl
        )
    }
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        avatarUrl = this.avatarUrl
    )
}

fun List<UserEntity>.toUsers(): List<User> {
    return this.map {
        User(
            it.id,
            it.name,
            it.avatarUrl
        )
    }
}