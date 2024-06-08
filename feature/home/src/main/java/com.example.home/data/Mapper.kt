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

fun List<UserResponse>.toEqualLocalDataBase(userEntity: List<UserEntity>?) : Boolean {
    if(userEntity.isNullOrEmpty())
        return false

    if (this.size != userEntity.size)
        return false

    val sortedResponse = this.sortedBy { it.login }
    val sortedEntity = userEntity.sortedBy { it.name }

    return sortedResponse.zip(sortedEntity).all { (response, entity) ->
        response.id == entity.id &&
                response.login == entity.name &&
                response.avatarUrl == entity.avatarUrl
    }
}
