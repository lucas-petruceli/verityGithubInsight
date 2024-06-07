package com.example.net.datasource

import com.example.net.ApiService
import com.example.net.model.GithubRepoResponse
import javax.inject.Inject

class GithubRepoDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchGithubRepo(username: String) : List<GithubRepoResponse> {
        return apiService.fetchGithubRepos(username)
    }
}