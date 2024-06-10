package com.example.user.data

sealed class PartialUserDetails {
    data class UserInfo(
        val name: String,
        val avatarUrl: String?,
        val location: String?,
        val followingCount: Int,
        val followerCount: Int
    ) : PartialUserDetails()

    data class GithubRepositories(
        val githubRepos: List<GithubRepo>
    ) : PartialUserDetails()
}