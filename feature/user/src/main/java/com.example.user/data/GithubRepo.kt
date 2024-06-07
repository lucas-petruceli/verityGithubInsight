package com.example.user.data

data class GithubRepo(
    val id: Long,
    val name: String,
    val isPrivate: Boolean,
    val forksCount: Int,
    val stargazersCount: Int
)
