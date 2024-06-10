package com.example.user


import com.example.net.datasource.GithubRepoDataSource
import com.example.net.datasource.UserRemoteDataSource
import com.example.user.data.repository.UserDetailsRepository
import com.example.user.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun providerUserRepository(
        userRemoteDataSource: UserRemoteDataSource,
        githubRepoDataSource: GithubRepoDataSource
    ): UserDetailsRepository {
        return UserRepositoryImpl(
            userRemoteDataSource, githubRepoDataSource
        )
    }
}