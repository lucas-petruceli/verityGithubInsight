package com.example.user.data.repository

import com.example.net.datasource.GithubRepoDataSource
import com.example.net.datasource.UserRemoteDataSource
import com.example.user.data.PartialUserDetails
import com.example.user.data.ResultCustom
import com.example.user.data.toListGithubRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val githubRepoDataSource: GithubRepoDataSource
) : UserDetailsRepository {
    override suspend fun fetchUserInfosDetails(username: String): Flow<ResultCustom> = flow {
        coroutineScope {
            val userRemoteDef = async { userRemoteDataSource.fetchUserDetails(username) }
            val githubRepoDef = async { githubRepoDataSource.fetchGithubRepo(username) }

            try {
                val userInfo = userRemoteDef.await()
                emit(
                    ResultCustom.Success.User(
                        PartialUserDetails.UserInfo(
                            name = userInfo.login,
                            avatarUrl = userInfo.avatarUrl,
                            location = userInfo.location,
                            followingCount = userInfo.following,
                            followerCount = userInfo.followers
                        )
                    )
                )
            } catch (e: Exception) {
                emit(ResultCustom.Error.User)
            }

            try {
                val githubRepoInfo = githubRepoDef.await()
                emit(
                    ResultCustom.Success.GitHubRepos(
                        PartialUserDetails.GithubRepositories(
                            githubRepos = githubRepoInfo.toListGithubRepo()
                        )
                    )
                )
            } catch (e: Exception) {
                emit(ResultCustom.Error.GitHubRepos)
            }
        }
    }
}