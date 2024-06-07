package com.example.user.data

import com.example.database.entity.UserEntity
import com.example.net.model.GithubRepoResponse
import com.example.net.model.UserDetailsResponse

fun UserDetailsResponse.toUserDetails(): UserDetails {
    return UserDetails(
        id = this.id,
        name = this.login,
        avatarUrl = this.avatarUrl
    )
}

fun UserEntity.toUserDetails(): UserDetails {
    return UserDetails(
        id = this.id,
        name = this.name,
        avatarUrl = this.avatarUrl
    )
}

fun List<GithubRepoResponse>.toListGithubRepo(): List<GithubRepo> {
    return this.map {
        GithubRepo(
            id = it.id,
            name = it.name,
            isPrivate = it.isPrivate,
            forksCount = it.forksCount,
            stargazersCount = it.stargazersCount
        )
    }
}