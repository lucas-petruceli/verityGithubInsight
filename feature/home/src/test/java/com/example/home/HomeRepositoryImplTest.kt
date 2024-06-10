package com.example.home

import com.example.database.datasource.UserLocalDataSource
import com.example.database.entity.UserEntity
import com.example.home.data.repository.HomeRepositoryImpl
import com.example.home.data.toListUser
import com.example.home.data.toUsers
import com.example.net.datasource.UserRemoteDataSource
import com.example.net.model.UserResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class HomeRepositoryImplTest {

    private lateinit var userRemoteDataSource: UserRemoteDataSource
    private lateinit var userLocalDataSource: UserLocalDataSource
    private lateinit var repository: HomeRepositoryImpl

    @Before
    fun setup() {
        userRemoteDataSource = mockk()
        userLocalDataSource = mockk()
        repository = HomeRepositoryImpl(userRemoteDataSource, userLocalDataSource)
    }

    @Test
    fun `fetchUsers should emit local users if available`() = runTest {
        val localUsers = listOf(UserEntity(1, "Lucas", "https://example.com/lucas.jpg"))
        val remoteUsers = listOf(getUserResponseMock())
        coEvery { userLocalDataSource.getAll() } returns localUsers
        coEvery { userRemoteDataSource.fetchUsers() } returns remoteUsers

        val result = repository.fetchUsers().first()

        assertTrue(result.isSuccess)
        assertEquals(localUsers.toUsers().first(), remoteUsers.toListUser().first())
    }

    @Test
    fun `fetchUsers should emit failure if remote fetch fails and no local data`() = runTest {
        coEvery { userLocalDataSource.getAll() } returns null
        coEvery { userRemoteDataSource.fetchUsers() } throws Exception("Network error")

        val result = repository.fetchUsers().first()

        assertTrue(result.isFailure)
    }

    private fun getUserResponseMock() : UserResponse{
        return UserResponse(
            login = "Lucas",
            id = 1,
            nodeId = "MDQ6VXNlcjEyMzQ1Ng==",
            avatarUrl = "https://example.com/lucas.jpg",
            gravatarId = "",
            url = "https://api.example.com/users/mock_user",
            htmlUrl = "https://example.com/mock_user",
            followersUrl = "https://api.example.com/users/mock_user/followers",
            followingUrl = "https://api.example.com/users/mock_user/following{/other_user}",
            gistsUrl = "https://api.example.com/users/mock_user/gists{/gist_id}",
            starredUrl = "https://api.example.com/users/mock_user/starred{/owner}{/repo}",
            subscriptionsUrl = "https://api.example.com/users/mock_user/subscriptions",
            organizationsUrl = "https://api.example.com/users/mock_user/orgs",
            reposUrl = "https://api.example.com/users/mock_user/repos",
            eventsUrl = "https://api.example.com/users/mock_user/events{/privacy}",
            receivedEventsUrl = "https://api.example.com/users/mock_user/received_events",
            type = "User",
            siteAdmin = false
        )
    }
}