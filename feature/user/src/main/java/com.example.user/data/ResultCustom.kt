package com.example.user.data


sealed class ResultCustom {
    sealed class Success(data: PartialUserDetails) : ResultCustom() {
        data class User(val data: PartialUserDetails.UserInfo) : Success(data)
        data class GitHubRepos(val data: PartialUserDetails.GithubRepositories) : Success(data)
    }
    sealed class Error : ResultCustom() {
        data object User : Error()
        data object GitHubRepos : Error()
    }
}