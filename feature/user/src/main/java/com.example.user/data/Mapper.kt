package com.example.user.data


import com.example.net.model.GithubRepoResponse


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