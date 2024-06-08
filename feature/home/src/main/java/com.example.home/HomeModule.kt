package com.example.home

import com.example.database.datasource.UserLocalDataSource
import com.example.home.data.repository.HomeRepository
import com.example.home.data.repository.HomeRepositoryImpl
import com.example.net.datasource.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun providerHomeRepository(
        userRemoteDataSource: UserRemoteDataSource,
        userLocalDataSource: UserLocalDataSource
    ): HomeRepository {
        return HomeRepositoryImpl(userRemoteDataSource, userLocalDataSource)
    }
}