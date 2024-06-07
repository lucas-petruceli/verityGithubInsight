package com.example.home.data

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